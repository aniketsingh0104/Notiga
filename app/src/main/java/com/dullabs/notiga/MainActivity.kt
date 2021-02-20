package com.dullabs.notiga

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.dullabs.notiga.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mNavHostFragment: NavHostFragment
    private lateinit var mNavController: NavController
    private lateinit var mainBinding: ActivityMainBinding
    lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setupViews()
    }

    private fun setupViews() {
        mNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragNavHost) as NavHostFragment
        mNavController = mNavHostFragment.navController
//        NavigationUI.setupWithNavController(mainBinding.bottomNavView, mNavController)
        mainBinding.bottomNavView.setupWithNavController(mNavController)

        // setup toolbar with fragment_home, fragment_batch_time, fragment_apps_configure as toLevel screens
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.inboxFragment,
                R.id.batchTimeFragment,
                R.id.appsConfigureFragment
            ),
            mainBinding.drawerLayout
        )


        // setup navigation view with nav controller (side drawer)
        mainBinding.navView.setupWithNavController(mNavController)

        mainBinding.materialToolbar.setupWithNavController(mNavController, appBarConfiguration)

        // make drawer menu clickable
        mainBinding.navView.setNavigationItemSelectedListener(this)
    }

    //bottom nav
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(mNavController) ||
                super.onOptionsItemSelected(item)
    }


    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragNavHost).navigateUp(appBarConfiguration)
    }

    override fun onBackPressed() {
        if (mainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

}