package ru.awawa.weatherapp.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this

        setSupportActionBar(binding.root.findViewById(R.id.toolbar))
        setupNavigationDrawer()

        val fragmentManager = supportFragmentManager
        fragmentManager.registerFragmentLifecycleCallbacks(
            object: FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
                    super.onFragmentPaused(fm, f)

                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    var view = currentFocus
                    if (view == null) view = View(this@MainActivity)
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            },
            true
        )
    }


    private fun setupNavigationDrawer() {

        val drawerLayout = binding.root.findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = binding.root.findViewById<NavigationView>(R.id.navigationView)
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_currentWeather,
            R.id.nav_search
        )
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(
            this,
            R.id.navHostFragment
        )
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
