package com.example.junemon.kotlinnetworking.feature.search

import com.example.junemon.kotlinnetworking.base.BaseView
import com.example.junemon.kotlinnetworking.model.MainModelLastMatch
import com.example.junemon.kotlinnetworking.model.MainModelNextMatch
import com.example.junemon.kotlinnetworking.model.TeamModel

interface SerachView : BaseView {
    fun getLastData(data: List<MainModelLastMatch.Event>)
    fun getNextData(data: List<MainModelNextMatch.Event>)
    fun getAllTeam(data: List<TeamModel.Team>)
    fun onFailed(message: String)
}