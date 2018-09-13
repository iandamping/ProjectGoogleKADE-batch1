package com.example.junemon.kotlinnetworking

import android.app.Application
import com.example.junemon.kotlinnetworking.network.ApiClient

class MainApplication : Application() {
    companion object {
        val getFootballEvent by lazy {
            ApiClient.create()
        }
    }

}