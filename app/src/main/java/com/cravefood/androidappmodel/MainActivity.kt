package com.cravefood.androidappmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private var currentNavController: LiveData<NavController>? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		if (savedInstanceState == null) {
			setupBottomNavigationBar()
		}
	}

	override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
		super.onRestoreInstanceState(savedInstanceState)
		setupBottomNavigationBar()
	}

	/**
	 * Called on first creation and when restoring state.
	 */
	private fun setupBottomNavigationBar() {
		val navGraphIds = listOf(R.navigation.nav_products, R.navigation.nav_orders, R.navigation.nav_settings)

		// Setup the bottom navigation view with a list of navigation graphs
		val controller = bottomNavigationView.setupWithNavController(
			navGraphIds = navGraphIds,
			fragmentManager = supportFragmentManager,
			containerId = R.id.nav_host_container,
			intent = intent
		)

		// Whenever the selected controller changes, setup the action bar.
		setSupportActionBar(toolbar)
		controller.observe(this, Observer { navController ->
			setupActionBarWithNavController(navController)
		})
		currentNavController = controller
	}

	override fun onSupportNavigateUp(): Boolean {
		return currentNavController?.value?.navigateUp() ?: false
	}

	/**
	 * Overriding popBackStack is necessary in this case if the app is started from the deep link.
	 */
	override fun onBackPressed() {
		if (currentNavController?.value?.popBackStack() != true) {
			super.onBackPressed()
		}
	}
}
