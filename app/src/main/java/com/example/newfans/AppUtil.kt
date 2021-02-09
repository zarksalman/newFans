package com.example.newfans

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

/*
* Created by SalmanHameed on 12/16/2020.
*/

object AppUtil {

    fun isNetworkAvailable(activity: Activity): Boolean {
        var outcome = false
        val cm =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfos = cm.allNetworkInfo
        for (tempNetworkInfo in networkInfos) {
            /**
             * Can also check if the user is in roaming
             */
            if (tempNetworkInfo.isConnected) {
                outcome = true
                break
            }
        }
        return outcome
    }
}