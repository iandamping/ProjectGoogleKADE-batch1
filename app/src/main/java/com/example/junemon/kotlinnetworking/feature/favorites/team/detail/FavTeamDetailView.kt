package com.example.junemon.kotlinnetworking.feature.favorites.team.detail

import com.example.junemon.kotlinnetworking.base.BaseView
import com.example.junemon.kotlinnetworking.databases.DatabasesTeamModel

interface FavTeamDetailView:BaseView {
    fun successGetData(data: DatabasesTeamModel)
    fun failGetData(message: String)
}