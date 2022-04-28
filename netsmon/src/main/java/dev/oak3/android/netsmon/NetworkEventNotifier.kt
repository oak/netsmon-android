package dev.oak3.android.netsmon

import androidx.lifecycle.LiveData

/**
 * The notifier object
 */
object NetworkEventNotifier : LiveData<NetworkEvent>() {
    internal fun notify(networkEvent: NetworkEvent) {
        postValue(networkEvent)
    }
}