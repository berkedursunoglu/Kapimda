package com.berkedursunoglu.kapimda.presentation.ui.mainpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.data.models.Product
import com.berkedursunoglu.kapimda.databinding.ActivityMainPageBinding
import com.berkedursunoglu.kapimda.presentation.viewmodels.MainPageViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class MainPage : AppCompatActivity(){

    private lateinit var binding:ActivityMainPageBinding
    private val viewModel: MainPageViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_page)
        initNavigation()
        viewModel.getTotalPrice()
        viewModel.price.observe(this, Observer {
            binding.tvBasketBalance.text = it.toString()
        })
    }

    fun initNavigation(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController
        val navigationBottom = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(navigationBottom,navController)
    }

}