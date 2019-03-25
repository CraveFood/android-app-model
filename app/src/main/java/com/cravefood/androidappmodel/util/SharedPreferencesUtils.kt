package com.cravefood.androidappmodel.util

import android.content.Context
import android.content.SharedPreferences
import com.cravefood.androidappmodel.MyApp


const val GLOBAL_PREFS = "GlobalPrefs"

class SharedPreferencesUtils {

    fun saveStringIntoSharedPreferences(name: String, key: String, value: String) {
        getEditor(name).apply {
            putString(key, value)
            apply()
        }
    }

    fun saveIntIntoSharedPreferences(name: String, key: String, value: Int) {
        getEditor(name).apply {
            putInt(key, value)
            apply()
        }
    }

    fun saveBooleanIntoSharedPreferences(name: String, key: String, value: Boolean) {
        getEditor(name).apply {
            putBoolean(key, value)
            apply()
        }
    }

    fun getStringFromSharedPreferences(name: String, key: String): String {
        return getSharedPrefs(name).getString(key, "")
    }

    fun getIntFromSharedPreferences(name: String, key: String): Int {
        return getSharedPrefs(name).getInt(key, -1)
    }

    fun getBooleanFromSharedPreferences(name: String, key: String): Boolean {
        return getSharedPrefs(name).getBoolean(key, false)
    }

    fun getSharedPrefs(name: String): SharedPreferences {
        return MyApp.appContext.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun getEditor(name: String): SharedPreferences.Editor {
        return getSharedPrefs(name).edit()
    }

    fun removeFromSharedPreferences(name: String, key: String) {
        getEditor(name).apply {
            remove(key)
            apply()
        }
    }
}