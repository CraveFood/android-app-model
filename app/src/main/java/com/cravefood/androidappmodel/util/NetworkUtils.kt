package com.cravefood.androidappmodel.util

import android.content.Context
import android.net.ConnectivityManager


private const val BASE_URL_KEY = "baseUrlKey"
private const val WEBSOCKET_URL_KEY = "webSocketUrlKey"

class NetworkUtils(private val context: Context,
                   private val sharedPreferencesUtils: SharedPreferencesUtils,
                   private val apiBaseUrl: String) {

    fun isOnline(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

    fun getApiUrl(): String {
        var cachedUrl = sharedPreferencesUtils.getStringFromSharedPreferences(GLOBAL_PREFS, BASE_URL_KEY)

        if (cachedUrl.isEmpty()) {
            cachedUrl = apiBaseUrl
        }
        return cachedUrl
    }

    fun saveBaseApiUrlOnCache(url: String) {
        sharedPreferencesUtils.saveStringIntoSharedPreferences(GLOBAL_PREFS, BASE_URL_KEY, url)
    }

    fun saveWebSocketUrlOnCache(url: String) {
        sharedPreferencesUtils.saveStringIntoSharedPreferences(GLOBAL_PREFS, WEBSOCKET_URL_KEY, url)
    }

    fun getBaseApiUrlOnCache() = sharedPreferencesUtils.getStringFromSharedPreferences(GLOBAL_PREFS, BASE_URL_KEY)


    fun getWebSocketUrlOnCache() = sharedPreferencesUtils.getStringFromSharedPreferences(GLOBAL_PREFS, WEBSOCKET_URL_KEY)
}