package com.berkedursunoglu.kapimda.presentation.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.presentation.ui.mainpage.MainPage
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initNavigation()
        val firebase:FirebaseAuth = FirebaseAuth.getInstance()
        if(firebase.currentUser != null) {
            startActivity(Intent(this, MainPage::class.java))
            finish()
        }
    }

    fun initNavigation(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.loginContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val navigationBottom = findViewById<BottomNavigationView>(R.id.tabLayout)
        NavigationUI.setupWithNavController(navigationBottom,navController)
    }
}