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

    fun getLastData(idLastMatch: String?) {
        EspressoIdlingResource.increments()
        compositeDisposable.addAll(MainApplication.getFootballEvent.getSearchPastEventData(idLastMatch)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> mView.getLastData(result.events) },
                        { error -> mView.onFailed("Tidak Ada Data Pertandingan") },
                        { EspressoIdlingResource.decrements() }))

    }

    fun getNextData(data: String?) {
        EspressoIdlingResource.increments()
        compositeDisposable.add(MainApplication.getFootballEvent.getSearchNextEventData(data)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> mView.getNextData(result.events) },
                        { error -> mView.onFailed("Tidak Ada Data Pertandingan") },
                        { EspressoIdlingResource.increments() }))

    }

    fun getAllTeamDetails(data: String) {
        EspressoIdlingResource.increments()
        compositeDisposable.add(MainApplication.getFootballEvent.getTeamSearchDetails(data)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    getLastData(result.teams.get(0).idTeam)
                    getNextData(result.teams.get(0).idTeam)
                    mView.getAllTeam(result.teams)
                },
                        { error -> mView.onFailed("Team Tidak Ada") },
                        {
                            EspressoIdlingResource.increments()
                        }))
    }
}