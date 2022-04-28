package dev.oak3.android.netsmon

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log

/**
 * Network state implementation for android networks
 */
internal class AndroidNetworkState : NetworkState {
    private val TAG = AndroidNetworkState::class.java.name

    override var network: Network? = null
        set(value) {
            Log.d(TAG, "Network changed from ${field?.toString()} to ${value?.toString()}")
            field = value
        }

    override var isConnected: Boolean = false
        private set(value) {
            if (field == value) return
            Log.d(TAG, "isConnected changed on ${network?.toString()} from $field to $value")
            field = value
            NetworkEventNotifier.notify(if (value) NetworkEvent.ConnectivityAvailable else NetworkEvent.ConnectivityLost)
        }

    override var linkProperties: LinkProperties? = null
        set(value) {
            Log.d(TAG, "LinkProperties changed on network ${network.toString()}")
            val event = NetworkEvent.LinkPropertyChanged(field)
            field = value
            NetworkEventNotifier.notify(event)
        }

    override var networkCapabilities: NetworkCapabilities? = null
        set(value) {
            Log.d(TAG, "NetworkCapabilities changed on network ${network.toString()}")
            val event = NetworkEvent.NetworkCapabilityChanged(field)
            field = value
            NetworkEventNotifier.notify(event)
            isConnected = field?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
            isWifi = field?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false
            isMobile = field?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false
        }

    override var isWifi: Boolean = false
        private set

    override var isMobile: Boolean = false
        private set
}