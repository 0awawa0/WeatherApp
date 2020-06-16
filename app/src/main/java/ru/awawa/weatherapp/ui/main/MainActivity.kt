package ru.awawa.weatherapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this

        setSupportActionBar(binding.root.findViewById(R.id.toolbar))
        setupNavigationDrawer()
    }


    private fun setupNavigationDrawer() {

        val drawerLayout = binding.root.findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = binding.root.findViewById<NavigationView>(R.id.navigationView)
        val appBarConfiguration = AppBarConfiguration.Builder()
            .setDrawerLayout(drawerLayout)
            .build()

        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            appBarConfiguration
        )

        NavigationUI.setupWithNavController(navigationView, navController)
    }
}
