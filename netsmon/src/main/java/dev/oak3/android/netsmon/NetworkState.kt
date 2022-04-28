package dev.oak3.android.netsmon

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities

/**
 * Network state resources of interest
 */
interface NetworkState {
    val isConnected: Boolean
    val network: Network?
    val networkCapabilities: NetworkCapabilities?
    val linkProperties: LinkProperties?
    val isWifi: Boolean
    val isMobile: Boolean
}