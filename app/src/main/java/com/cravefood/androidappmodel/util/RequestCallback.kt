package com.cravefood.androidappmodel.util

import android.util.Log
import com.cravefood.androidappmodel.BuildConfig
import com.cravefood.androidappmodel.data.repository.repository_util.NoInternetConnectionException
import org.koin.standalone.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

abstract class RequestCallback<T> : Callback<T>, KoinComponent {

    abstract fun onRequestResponse(responseObject: T)

    abstract fun onRequestError(throwable: Throwable?)

    override fun onResponse(call: Call<T>, response: Response<T>) {

        if (response.isSuccessful) {

            if (response.body() != null) {
                response.body()?.let { onRequestResponse(it) } // Avoiding complex expression error
            } else {
                when (response.code()) {
                    202 -> {
                        val text = "RequestCallback: Cannot cast 'accepted' " +
                                "response (202) to 'Unit' type. \n\n" +
                                "The following information may be useful:\n" +
                                "URL: " + response.raw().request().url().toString() + "\n" +
                                "Method: " + response.raw().request().method()
                        handleNoBodyResponse(call, response, text)
                    }
                    204 -> {
                        val text = "RequestCallback: Cannot cast 'no content' " +
                                "response (204) to 'Unit' type. \n\n" +
                                "The following information may be useful:\n" +
                                "URL: " + response.raw().request().url().toString() + "\n" +
                                "Method: " + response.raw().request().method()
                        handleNoBodyResponse(call, response, text)
                    }
                    else -> {
                        onFailure(call, Exception("")) // Empty message will trigger default error
                    }
                }
            }
        } else {
            try {
                val errorBody = response.errorBody()?.string() ?: ""
                val errorMessage = errorBody

                when (response.code()) {
//                    401 -> sessionRepository.onUnauthorizedAccess(errorMessage)
                    400 -> handleBadRequest(errorBody, call, Exception(errorMessage))
                    404 -> onNotFoundResponse(response.message())
                    403 -> onFailure(call, Exception(errorMessage))
                    500 -> onFailure(call, Exception(errorMessage))
                    502 -> {
                        val url = response.raw().request().url().toString()

                        onFailure(call, Exception(response.message()))
                    }
                    else -> onFailure(call, Exception(response.message()))
                }
            } catch (e: IOException) {
                onFailure(call, Exception("wrong_parameters_for_this_task"))
            } catch (e: NullPointerException) {
                onFailure(call, Exception("wrong_parameters_for_this_task"))
            }
        }
    }

    private fun handleNoBodyResponse(call: Call<T>, response: Response<T>, errorText: String) {
        try {
            @Suppress("UNCHECKED_CAST")
            onRequestResponse(Unit as T)
        } catch (e: ClassCastException) {
            onFailure(call, Exception())
        }
    }

    override fun onFailure(call: Call<T>, throwable: Throwable?) {
        if (BuildConfig.DEBUG) {
            Log.e("RequestCallback", call.request().url().toString(), throwable)
        }

        return if (throwable is NoInternetConnectionException) {
            onNoInternetConnection("check_your_internet_connection")
        } else {
            onRequestError(throwable)
        }
    }

    private fun handleBadRequest(errorString: String, call: Call<T>, throwable: Throwable?) {
        onFailure(call, throwable) // adding this to keep the current behaviour
    }


    open fun onNoInternetConnection(message: String) {
        onRequestError(NoInternetConnectionException(message))
    }

    open fun onNotFoundResponse(message: String) {
        onRequestError(Throwable(message))
    }
}