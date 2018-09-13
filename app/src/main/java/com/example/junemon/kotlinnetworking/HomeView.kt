package com.example.junemon.kotlinnetworking

import com.example.junemon.kotlinnetworking.base.BaseView
import com.example.junemon.kotlinnetworking.model.AllLeagueModel

interface HomeView : BaseView {
    fun onSuccess(data: List<AllLeagueModel.League>)
    fun onFailed(message: String)
}