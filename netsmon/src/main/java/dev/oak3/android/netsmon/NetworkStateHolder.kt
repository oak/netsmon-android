package dev.oak3.android.netsmon

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest

/**
 * Holder object for statuses and initialization of monitoring
 */
object NetworkStateHolder {
    private lateinit var netStates: ArrayList<AndroidNetworkState>

    val isConnected: Boolean
        get() = netStates.any { h -> h.isConnected }

    val isWifi: Boolean
        get() = netStates.any { h -> h.isWifi }

    val isMobile: Boolean
        get() = netStates.none { h -> h.isWifi } && netStates.any { h -> h.isMobile }

    fun getNetworks() = netStates.map { h -> h.network }

    fun getNetworkCapabilities() = netStates.map { h -> h.networkCapabilities }

    fun getLinkProperties() = netStates.map { h -> h.linkProperties }

    fun Application.registerConnectivityMonitor() {
        netStates = arrayListOf()
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder().build(),
            AndroidNetworkCallback(netStates)
        )
    }
}
