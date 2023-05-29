package com.example.bn

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.airbnb.lottie.LottieAnimationView

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var logo: ImageView
    private lateinit var appName: ImageView
    private lateinit var splashImg: ImageView
    private lateinit var lottieAnimationView: LottieAnimationView
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: ScreenSlidePagerAdapter

    companion object {
        private const val NUM_PAGES = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        logo = findViewById(R.id.logo)
        appName = findViewById(R.id.app_name)
        splashImg = findViewById(R.id.background)
        lottieAnimationView = findViewById(R.id.lottie_animation)

        viewPager = findViewById(R.id.pager)
        pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter

        splashImg.animate().translationY(-2700f).setDuration(1000).startDelay = 4000
        logo.animate().translationY(2000f).setDuration(1000).startDelay = 4000
        appName.animate().translationY(2000f).setDuration(1000).startDelay = 4000
        lottieAnimationView.animate().translationY(2000f).setDuration(1000).setStartDelay(4000)
            .withEndAction {

            }
            .start()

    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> OnBoarding1Fragment()
                1 -> OnBoarding2Fragment()
                2 -> OnBoarding3Fragment()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }

        override fun getCount(): Int {
            return NUM_PAGES
        }
    }
}