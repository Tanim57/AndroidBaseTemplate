package com.tanim.androidbasetemplate.utils

import android.content.Context
import javax.inject.Singleton
import javax.inject.Inject
import kotlin.jvm.Synchronized
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.lang.Exception

@Singleton
class NetworkUtils @Inject constructor(private val context: Context) {
    @get:Synchronized
    val isNetworkConnected: Boolean
        get() = try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var info: NetworkInfo? = null
            info = cm.activeNetworkInfo
            if (info == null) false
            val network = info?.state
            network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING
        } catch (e: Exception) {
            false
        }

    companion object {
        fun isNetworkConnectionAvailable(context: Context): Boolean {
            return try {
                val cm =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                var info: NetworkInfo? = null
                info = cm.activeNetworkInfo
                if (info == null) return false
                val network = info.state
                network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING
            } catch (e: Exception) {
                false
            }
        }
//
//        val isNetworkConnectionAvailable: Boolean
//            get() = try {
//                val cm = App.getContext()
//                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//                var info: NetworkInfo? = null
//                if (cm != null) {
//                    info = cm.activeNetworkInfo
//                }
//                if (info == null) false
//                val network = info?.state
//                network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING
//            } catch (e: Exception) {
//                false
//            }
    }
}