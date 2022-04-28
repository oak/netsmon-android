package dev.oak3.android.netsmon

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities

/**
 * Callback implementation class to change and map data on network connections
 */
internal class AndroidNetworkCallback(private val holders: ArrayList<AndroidNetworkState>) :
    ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network) {
        if (holders.none { h -> h.network == network }) {
            holders.add(AndroidNetworkState().apply { this.network = network })
        }
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        holders.find { t -> t.network == network }.apply {
            this?.networkCapabilities = networkCapabilities
        }
    }

    override fun onLost(network: Network) {
        val holderLost = holders.find { t -> t.network == network }
        holderLost?.linkProperties = null
        holderLost?.networkCapabilities = null
        holderLost?.network = null
        holders.remove(holderLost)
    }

    override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
        holders.find { t -> t.network == network }.apply {
            this?.linkProperties = linkProperties
        }
    }
}