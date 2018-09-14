package com.example.junemon.kotlinnetworking.feature.team

import com.example.junemon.kotlinnetworking.base.BaseFragmentView
import com.example.junemon.kotlinnetworking.model.TeamModel

interface TeamView : BaseFragmentView {
    fun onSuccessData(data: List<TeamModel.Team>)
    fun onFail(message: String)
}