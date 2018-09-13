package com.example.junemon.kotlinnetworking.feature.nextmatch

import com.example.junemon.kotlinnetworking.base.BaseFragmentView
import com.example.junemon.kotlinnetworking.databases.DatabaseLeagueModel
import com.example.junemon.kotlinnetworking.model.MainModelNextMatch

interface NextMatchFragmentView : BaseFragmentView {
    fun onSuccess(data: List<MainModelNextMatch.Event>)
    fun onFailed(message: String)

}