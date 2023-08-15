package com.example.retrofitproject.ui.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.retrofitproject.R
import com.example.retrofitproject.databinding.ActivityDrawerBinding

class DrawerActivity : AppCompatActivity() {

    lateinit var binding: ActivityDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController =navHostFragment.navController

        binding.navView.setupWithNavController(navController)
    }
}