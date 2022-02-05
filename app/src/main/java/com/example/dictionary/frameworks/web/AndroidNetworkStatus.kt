package com.example.dictionary.frameworks.web

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import io.reactivex.subjects.BehaviorSubject

class AndroidNetworkStatus(context: Context) : IAndroidNetworkStatus {
    private val networkBehaviorSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()
    private val connectivityManager = context.getSystemService<ConnectivityManager>()

    init {
        val request = NetworkRequest.Builder().build()

        connectivityManager?.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                networkBehaviorSubject.onNext(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                networkBehaviorSubject.onNext(false)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                networkBehaviorSubject.onNext(false)
            }
        })
    }

    override fun isNetworkAvailable(): Boolean {
        return networkBehaviorSubject.value ?: false
    }
}