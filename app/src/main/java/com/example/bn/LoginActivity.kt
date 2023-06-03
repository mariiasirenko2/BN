package com.example.bn

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.bn.api.SessionManager
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var fb: FloatingActionButton
    private lateinit var google: FloatingActionButton
    private lateinit var twitter: FloatingActionButton
    private var message = "BeautyNote App 1.0 support login only via Facebook"
    private lateinit var callbackManager: CallbackManager
    private val sessionManager = SessionManager.getInstance()

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

        val adapter = LoginAdapter(supportFragmentManager, tabLayout.tabCount)
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

        callbackManager = CallbackManager.Factory.create()

        var tokenString: String

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    Toast.makeText(this@LoginActivity, "Cancel", Toast.LENGTH_SHORT).show()

                }

                override fun onError(error: FacebookException) {
                    Toast.makeText(this@LoginActivity, "Error", Toast.LENGTH_SHORT).show()
                }

                override fun onSuccess(result: LoginResult) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Get Token from facebook",
                        Toast.LENGTH_SHORT
                    ).show()
                    tokenString = result.accessToken.token

                    sessionManager.getCoroutineScope().launch {
                        performAuthenticate(tokenString)
                    }
                }
            })

        fb.setOnClickListener {
            LoginManager.getInstance()
                .logInWithReadPermissions(this, listOf("public_profile,email"))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private suspend fun performAuthenticate(token: String) {
        try {
            val response = sessionManager.getUserApi().authenticate(token)
            if (response.isSuccessful) {
                val jwtResponse = response.body()?.string()
                val jsonObject = JSONObject(jwtResponse)
                val jwt = jsonObject.getString("jwt")

                Toast.makeText(this, jwt, Toast.LENGTH_SHORT).show()
                sessionManager.getCoroutineScope().launch {
                        performGetMe( "Bearer $jwt")
                }
            } else {
                val errorBody = response.errorBody()?.string()
                Toast.makeText(this, "Error: $errorBody", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }}

    private suspend fun performGetMe(token: String) {
        try {
            sessionManager.setToken(token)
            val response = sessionManager.getUserApi().getMe(token)
            if (response.isSuccessful) {
                val user = response.body()
                if (user != null) {
                    sessionManager.setUser(user)
                }

                if (user?.role != "IN_DECIDE"){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Good", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "Need to choose role. Go to sign up page", Toast.LENGTH_SHORT).show()
                }
            } else {
                val errorBody = response.errorBody()?.string()
                Toast.makeText(this, "Error: $errorBody", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }}


}
