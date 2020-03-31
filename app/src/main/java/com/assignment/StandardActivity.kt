package com.assignment

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.assignment.receiver.ConnectivityReceiver


abstract class StandardActivity : AppCompatActivity(),

    ConnectivityReceiver.ConnectivityReceiverListener {


    var isConnected: Boolean = false
    var receiver: ConnectivityReceiver? = null

    lateinit var networkCallback: ConnectivityManager.NetworkCallback

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            networkCallback = ConnectivityManager.NetworkCallback()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiver = ConnectivityReceiver()
        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }


    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        this.isConnected = isConnected
    }

    fun <T : ViewModel> getViewModel(clazz: Class<T>): T {
        return ViewModelProviders.of(this).get(clazz)
    }

    open fun isConnectedToInternet(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.activeNetworkInfo ?: return false
        return true
    }


}
