package com.example.junemon.kotlinnetworking.feature.mainscreen

import com.example.junemon.kotlinnetworking.base.BaseView
import com.example.junemon.kotlinnetworking.databases.DatabaseLeagueModel
import com.example.junemon.kotlinnetworking.model.AllLeagueModel

interface MainScreenView : BaseView {
    fun onSuccess(data: List<AllLeagueModel.League>)
    fun onFailed(message: String)
    fun onShowDbData(data: List<DatabaseLeagueModel>)
}