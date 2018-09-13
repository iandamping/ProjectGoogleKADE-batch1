package com.example.junemon.kotlinnetworking.feature.favorites.detail

import android.content.Context
import com.example.junemon.kotlinnetworking.base.BasePresenter
import com.example.junemon.kotlinnetworking.databases.DatabaseModel

class FavDetailPresenter(var mView: FavDetailView) : BasePresenter {

    lateinit var ctx: Context
    override fun getContext(): Context {
        return ctx
    }

    override fun onCreate(context: Context) {
        this.ctx = context
        mView.initView()
    }


    fun getData(listData: DatabaseModel) {
        mView.successGetData(listData)
    }

    override fun onPause() {

    }

    override fun onDestroy() {
    }
}