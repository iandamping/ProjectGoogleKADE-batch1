package com.example.junemon.kotlinnetworking.feature.favorites.team

import com.example.junemon.kotlinnetworking.base.BaseFragmentView
import com.example.junemon.kotlinnetworking.databases.DatabasesTeamModel

interface FavTeamView : BaseFragmentView {
    fun onSuccesGetData(data: List<DatabasesTeamModel>)
    fun onFailedGetData(message: String)
}