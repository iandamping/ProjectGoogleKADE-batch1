package com.example.junemon.kotlinnetworking.feature.favorites.detail

import com.example.junemon.kotlinnetworking.base.BaseView
import com.example.junemon.kotlinnetworking.databases.DatabaseModel

interface FavDetailView : BaseView {
    fun successGetData(data: DatabaseModel)
    fun failGetData(message: String)
}