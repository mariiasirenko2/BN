package com.example.bn

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var logo: ImageView
    private lateinit var appName: ImageView
    private lateinit var splashImg: ImageView
    private lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        logo = findViewById(R.id.logo)
        appName = findViewById(R.id.app_name)
        splashImg = findViewById(R.id.background)
        lottieAnimationView = findViewById(R.id.lottie_animation)

        splashImg.animate().translationY(-2700f).setDuration(1000).startDelay = 4000
        logo.animate().translationY(2000f).setDuration(1000).startDelay = 4000
        appName.animate().translationY(2000f).setDuration(1000).startDelay = 4000
        lottieAnimationView.animate().translationY(2000f).setDuration(1000).startDelay = 4000
    }
}