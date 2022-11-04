package com.berkedursunoglu.kapimda.presentation.ui.mainpage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.databinding.ActivityMainPageBinding
import com.berkedursunoglu.kapimda.presentation.viewmodels.MainPageViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainPage : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding
    private val viewModel: MainPageViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_page)
        initNavigation()
        viewModel.getTotalPrice()
        viewModel.price.observe(this, Observer {
            binding.tvBasketBalance.text = it.toString()
        })

        binding.btnBasket.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction().addToBackStack("ProductFragment")
            fragmentManager.beginTransaction().addToBackStack("SearchFragment")
            fragmentManager.beginTransaction().addToBackStack("DetailFragment")
            fragmentTransaction.replace(com.berkedursunoglu.kapimda.R.id.fragmentContainerViewBasket, BasketFragment()).commit()
            binding.fragmentContainerViewBasket.visibility = View.VISIBLE
            binding.bottomNavigationView.visibility = View.GONE
        }
    }

    fun initNavigation(){
        val navHostFragment = supportFragmentManager.findFragmentById(com.berkedursunoglu.kapimda.R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        val navigationBottom = findViewById<BottomNavigationView>(com.berkedursunoglu.kapimda.R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(navigationBottom,navController)
    }

}