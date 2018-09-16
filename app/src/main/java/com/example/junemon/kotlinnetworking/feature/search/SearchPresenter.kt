package com.example.junemon.kotlinnetworking.feature.search

import android.content.Context
import com.example.junemon.kotlinnetworking.MainApplication
import com.example.junemon.kotlinnetworking.base.BasePresenter
import com.example.junemon.kotlinnetworking.helper.EspressoIdlingResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchPresenter(var mView: SerachView) : BasePresenter {
    lateinit var ctx: Context
    lateinit var compositeDisposable: CompositeDisposable


    override fun getContext(): Context? {
        return ctx
    }

    override fun onCreate(context: Context) {
        this.ctx = context
        mView.initView()
        compositeDisposable = CompositeDisposable()
    }

    override fun onPause() {

    }

    override fun onDestroy() {

    }

    fun getLastData(idLastMatch: String? = "4328") {
        EspressoIdlingResource.increments()
        compositeDisposable.addAll(MainApplication.getFootballEvent.getPastEventData(idLastMatch)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> mView.getLastData(result.events) },
                        { error -> mView.onFailed(error.localizedMessage) },
                        { EspressoIdlingResource.decrements() }))

    }

    fun getNextData(data: String = "4328") {
        compositeDisposable.add(MainApplication.getFootballEvent.getNextEventData(data)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> mView.getNextData(result.events) },
                        { error -> mView.onFailed(error.localizedMessage) }))

    }

    fun getAllTeam(data: String = "4328") {
        compositeDisposable.add(MainApplication.getFootballEvent.getAllTeamDetail(data)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> mView.getAllTeam(result.teams) },
                        { error -> mView.onFailed(error.localizedMessage) }))
    }
}