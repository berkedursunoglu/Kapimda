package com.berkedursunoglu.kapimda.presentation.ui.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.presentation.ui.login.LoginActivity

class OnboardingActivity : AppCompatActivity() {

    private lateinit var shared:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        initNavigation()
        isFirst()
    }



    fun initNavigation(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.onboardingContainerView) as NavHostFragment
    }

    fun isFirst(){
        shared = this@OnboardingActivity.getSharedPreferences("onBoarding",Context.MODE_PRIVATE)
        var isFirst = shared.getBoolean("invisibleOnboarding", true)
        if (!isFirst){
            startActivity(Intent(this@OnboardingActivity, LoginActivity::class.java))
            finish()
        }
    }
}