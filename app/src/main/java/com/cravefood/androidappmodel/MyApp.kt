package com.cravefood.androidappmodel

import android.app.Application
import android.content.Context
import com.cravefood.androidappmodel.di.CommonModules
import com.cravefood.androidappmodel.di.RepositoryModules
import com.cravefood.androidappmodel.di.ViewModelModules
import org.koin.android.ext.android.startKoin

class MyApp : Application() {


    override fun onCreate() {
        super.onCreate()

        MyApp.instance = this

        val appModules = listOf(
            ViewModelModules,
            RepositoryModules,
            CommonModules
        )

        startKoin(this, appModules)
    }

    companion object {

        lateinit var instance: MyApp

        val appContext: Context
            get() = instance.applicationContext
    }


}