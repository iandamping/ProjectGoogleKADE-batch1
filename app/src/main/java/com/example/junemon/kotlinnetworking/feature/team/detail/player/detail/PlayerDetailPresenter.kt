package com.example.junemon.kotlinnetworking.feature.team.detail.player.detail

import android.content.Context
import com.example.junemon.kotlinnetworking.base.BasePresenter
import com.example.junemon.kotlinnetworking.model.AllPlayer
import io.reactivex.disposables.CompositeDisposable

class PlayerDetailPresenter(var mView: PlayerDetailView) : BasePresenter {

    lateinit var ctx: Context
    lateinit var compositeDisposable: CompositeDisposable


    override fun getContext(): Context {
        return ctx
    }

    override fun onCreate(context: Context) {
        this.ctx = context
        mView.initView()
        compositeDisposable = CompositeDisposable()
    }

    fun getPlayerDetail(allData: AllPlayer.Player) {
        mView.onGetData(allData)
    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }
}