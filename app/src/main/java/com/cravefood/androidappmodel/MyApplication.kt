package com.cravefood.androidappmodel

import android.app.Application
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {

	override fun onCreate() {
		super.onCreate()

		configureKoin()
	}

	private fun configureKoin() {
		val appModules = listOf(ViewModelModules)

		startKoin(this, appModules)
	}
}