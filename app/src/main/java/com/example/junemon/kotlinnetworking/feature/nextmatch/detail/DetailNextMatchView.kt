package com.example.junemon.kotlinnetworking.feature.nextmatch.detail

import com.example.junemon.kotlinnetworking.base.BaseView
import com.example.junemon.kotlinnetworking.model.MainModelNextMatch

interface DetailNextMatchView : BaseView {
    fun onSuccess(data: MainModelNextMatch.Event)
    fun onFailed(message: String)
}