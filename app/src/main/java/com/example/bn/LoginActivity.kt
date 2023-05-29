package com.example.bn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var fb: FloatingActionButton
    private lateinit var google: FloatingActionButton
    private lateinit var twitter: FloatingActionButton
    private var message = "BeautyNote App 1.0 support login only via Facebook"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)
        fb = findViewById(R.id.fab_fb)
        google = findViewById(R.id.fab_google)
        twitter = findViewById(R.id.fab_twitter)

        tabLayout.addTab(tabLayout.newTab().setText("Login"))
        tabLayout.addTab(tabLayout.newTab().setText("Signup"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = LoginAdapter(supportFragmentManager, this, tabLayout.tabCount)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        fb.translationY = 300f
        google.translationY = 300f
        twitter.translationY = 300f
        tabLayout.translationY = 300f

        fb.alpha = 0f
        google.alpha = 0f
        twitter.alpha = 0f
        tabLayout.alpha = 0f

        fb.animate().translationY(0f).alpha(1f).duration = 1000
        google.animate().translationY(0f).alpha(1f).duration = 1000
        twitter.animate().translationY(0f).alpha(1f).duration = 1000
        tabLayout.animate().translationY(0f).alpha(1f).duration = 1000

        fb.animate().startDelay = 400
        google.animate().startDelay = 600
        twitter.animate().startDelay = 800
        tabLayout.animate().startDelay = 100

        fb.animate().start()
        google.animate().start()
        twitter.animate().start()
        tabLayout.animate().start()

        google.setOnClickListener {
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
        twitter.setOnClickListener {
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }

    }
}
