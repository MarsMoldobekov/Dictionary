package com.example.dictionary.frameworks.web

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import kotlinx.coroutines.flow.MutableStateFlow

class AndroidNetworkStatus(context: Context) : IAndroidNetworkStatus {
    private val networkState = MutableStateFlow(false)
    private val connectivityManager = context.getSystemService<ConnectivityManager>()

    init {
        val request = NetworkRequest.Builder().build()

        connectivityManager?.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                networkState.value = true
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                networkState.value = false
            }

            override fun onUnavailable() {
                super.onUnavailable()
                networkState.value = false
            }
        })
    }

    override fun isNetworkAvailable(): Boolean {
        return networkState.value
    }
}