package com.example.junemon.kotlinnetworking.feature.team.detail.player.detail

import com.example.junemon.kotlinnetworking.base.BaseView
import com.example.junemon.kotlinnetworking.model.AllPlayer
import com.example.junemon.kotlinnetworking.model.PlayerDetailModel

interface PlayerDetailView : BaseView {
    fun onGetData(allData : AllPlayer.Player)
    fun failGetData(message: String)
}