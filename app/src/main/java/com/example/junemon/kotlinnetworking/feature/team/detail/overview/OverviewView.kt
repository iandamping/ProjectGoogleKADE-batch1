package com.example.junemon.kotlinnetworking.feature.team.detail.overview

import com.example.junemon.kotlinnetworking.base.BaseFragmentView
import com.example.junemon.kotlinnetworking.model.TeamDetailModel

interface OverviewView : BaseFragmentView {
    fun onSuccessData(data: List<TeamDetailModel.Team>)
    fun onFail(message: String)
}