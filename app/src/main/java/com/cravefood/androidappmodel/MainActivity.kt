package com.cravefood.androidappmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

	private lateinit var navController: NavController
	private lateinit var appBarConfiguration: AppBarConfiguration

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		configureActionBar()
	}

	private fun configureActionBar() {
		navController = findNavController(this, R.id.nav_host_fragment)
		setupActionBarWithNavController(this, navController)

		appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
	}

	override fun onSupportNavigateUp(): Boolean {
		return NavigationUI.navigateUp(navController, appBarConfiguration)
	}
}
