@file:Suppress("DEPRECATION")

package com.xmolnia.vicQuiz

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData

/**
 * Created by Osman Boy on 10.04.2021.
 **/
class NetWorkConnection(private val context: Context):LiveData<Boolean>(){

    private val connectivityManager : ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private lateinit var networkCallback : ConnectivityManager.NetworkCallback


    @SuppressLint("ObsoleteSdkInt")
    override fun onActive() {
        super.onActive()
        updateConnection()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                try{
                connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallback())}
                catch (e: java.lang.IllegalArgumentException){
                    e.printStackTrace()
                    Log.d("OS","35")
                }
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                lollipopNetWorkRequest()

            }
            else ->{
                context.registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            try {
                connectivityManager.unregisterNetworkCallback(connectivityManagerCallback())
            }
            catch (e: java.lang.IllegalArgumentException){
                    e.printStackTrace()
                Log.d("OS","56")
                }
        }
        else{
            context.unregisterReceiver(networkReceiver)
        }
    }



    @SuppressLint("ObsoleteSdkInt")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun lollipopNetWorkRequest(){
        val requestBuilder = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)

        try{
        connectivityManager.registerNetworkCallback(requestBuilder.build(), connectivityManagerCallback())
            }
        catch (e: java.lang.IllegalArgumentException){
            e.printStackTrace()
            Log.d("OS","77")
        }

    }



    private fun connectivityManagerCallback():ConnectivityManager.NetworkCallback {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            networkCallback = object :ConnectivityManager.NetworkCallback(){


                override fun onLost(network: Network) {
                    super.onLost(network)
                    postValue(false) }


                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    postValue(true) }
            }
            return networkCallback
        }

        else{ throw java.lang.IllegalArgumentException("Error404") }
    }




    private val networkReceiver = object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            updateConnection()
        }

    }

    private fun updateConnection(){
        val activeNetwork :NetworkInfo? = connectivityManager.activeNetworkInfo
        postValue(activeNetwork?.isConnected == true)
    }


}