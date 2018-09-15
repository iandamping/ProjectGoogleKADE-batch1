package com.example.junemon.kotlinnetworking.feature.favorites.team.detail

import android.content.Context
import com.example.junemon.kotlinnetworking.base.BasePresenter
import com.example.junemon.kotlinnetworking.databases.DatabasesTeamModel

class FavTeamDetailPresenter(var mView: FavTeamDetailView) : BasePresenter {
    lateinit var ctx: Context
    override fun getContext(): Context? {
        return ctx
    }

    override fun onCreate(context: Context) {
        this.ctx = context
        mView.initView()
    }

    fun getData(listData: DatabasesTeamModel) {
        mView.successGetData(listData)
    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }
}