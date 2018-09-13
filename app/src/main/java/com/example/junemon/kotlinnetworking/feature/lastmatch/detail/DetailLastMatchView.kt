package com.example.junemon.kotlinnetworking.feature.lastmatch.detail

import com.example.junemon.kotlinnetworking.base.BaseView
import com.example.junemon.kotlinnetworking.model.MainModelLastMatch

interface DetailLastMatchView : BaseView {
    fun successGetData(data: MainModelLastMatch.Event)
    fun failGetData(message: String)

}