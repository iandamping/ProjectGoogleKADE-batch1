package com.example.junemon.kotlinnetworking.feature.lastmatch

import com.example.junemon.kotlinnetworking.base.BaseFragmentView
import com.example.junemon.kotlinnetworking.model.MainModelLastMatch

interface LastMatchFragmentView : BaseFragmentView {
    fun onSuccess(data: List<MainModelLastMatch.Event>)
    fun onFailed(message: String)
}