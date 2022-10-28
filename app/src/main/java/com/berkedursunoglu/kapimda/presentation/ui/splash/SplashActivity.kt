package com.berkedursunoglu.kapimda.presentation.ui.splash

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.databinding.ActivitySplashBinding
import com.berkedursunoglu.kapimda.presentation.ui.onboarding.OnboardingActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySplashBinding
    private var currentProgress:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.progressBar.max = 100
        var handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable{
            override fun run() {
                binding.progressBar.progress = currentProgress
                currentProgress += 1;
                handler.postDelayed(this,10)
                if(currentProgress == 100){
                    startActivity(Intent(this@SplashActivity,OnboardingActivity::class.java))
                    finish()
                }
            }
        })
    }
}