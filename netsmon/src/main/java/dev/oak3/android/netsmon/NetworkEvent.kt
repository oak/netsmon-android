package dev.oak3.android.netsmon

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities

/**
 * Events emitted
 */
sealed class NetworkEvent {
    object ConnectivityLost : NetworkEvent()
    object ConnectivityAvailable : NetworkEvent()

    data class NetworkCapabilityChanged(val old: NetworkCapabilities?) : NetworkEvent()
    data class LinkPropertyChanged(val old: LinkProperties?) : NetworkEvent()
}