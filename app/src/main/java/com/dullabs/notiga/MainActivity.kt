package com.dullabs.notiga

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.dullabs.notiga.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable

class MainActivity : AppCompatActivity() {
    private lateinit var mNavHostFragment: NavHostFragment
    private lateinit var mNavController: NavController
    private lateinit var mainBinding: ActivityMainBinding
    lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        curveCornersOfAppBar()
        setupViews()
    }

    private fun setupViews() {
        mNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragNavHost) as NavHostFragment
        mNavController = mNavHostFragment.navController
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
        // setup top app bar (toolbar) with nav controller
        mainBinding.materialToolbar.setupWithNavController(mNavController, appBarConfiguration)
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

    fun getMainBinding(): ActivityMainBinding {
        return mainBinding
    }

    private fun curveCornersOfAppBar() {
        val radius = resources.getDimension(R.dimen.corner_radius)
        val bottomBarBackground = mainBinding.bottomAppBar.background as MaterialShapeDrawable
        bottomBarBackground.shapeAppearanceModel = bottomBarBackground.shapeAppearanceModel
            .toBuilder()
            .setTopRightCorner(CornerFamily.ROUNDED, radius)
            .setTopLeftCorner(CornerFamily.ROUNDED, radius)
            .setBottomRightCorner(CornerFamily.ROUNDED, radius)
            .setBottomLeftCorner(CornerFamily.ROUNDED, radius)
            .build()
    }

    fun hideBottomBar() {
        mainBinding.bottomAppBar.visibility = View.GONE
    }

    fun showBottomBar() {
        mainBinding.bottomAppBar.visibility = View.VISIBLE
    }

    fun hideFab() {
        mainBinding.fabBottomNav.visibility = View.GONE
    }

    fun showFab() {
        mainBinding.fabBottomNav.visibility = View.VISIBLE
    }

}