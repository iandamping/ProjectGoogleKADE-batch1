package com.example.junemon.kotlinnetworking.feature.team.detail.player

import com.example.junemon.kotlinnetworking.base.BaseFragmentView
import com.example.junemon.kotlinnetworking.model.AllPlayer

interface PlayerView:BaseFragmentView {
    fun onSuccessData(data: List<AllPlayer.Player>)
    fun onFail(message: String)
}