package com.example.junemon.kotlinnetworking.feature.lastmatch

import android.content.Context
import android.view.View
import com.example.junemon.kotlinnetworking.MainApplication
import com.example.junemon.kotlinnetworking.base.BaseFragmentPresenter
import com.example.junemon.kotlinnetworking.helper.EspressoIdlingResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LastMatchFragmentPresenter(var mView: LastMatchFragmentView) : BaseFragmentPresenter {
    private var ctx: Context? = null
    lateinit var composite: CompositeDisposable


    override fun onAttach(context: Context?) {
        this.ctx = context
        composite = CompositeDisposable()

    }

    fun getData(idLastMatch: String? = "4328") {
        EspressoIdlingResource.increments()
        composite.addAll(MainApplication.getFootballEvent.getPastEventData(idLastMatch)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> mView.onSuccess(result.events) },
                        { error -> mView.onFailed(error.localizedMessage) },
                        { EspressoIdlingResource.decrements() }))

    }


    override fun onCreateView(view: View) {
        mView.initView(view)
    }

//    fun getAllData() {
//        EspressoIdlingResource.increments()
//        ctx?.database?.use {
//            val result = select(DatabaseLeagueModel.TABLE_LEAGUE)
//            val favorite = result.parseList(classParser<DatabaseLeagueModel>())
//            mView.allLeagueData(favorite)
//            EspressoIdlingResource.decrements()
//        }
//    }

//    fun getAllDataBySearch(leagueName: String = "English Premier League") {
//        EspressoIdlingResource.increments()
//        composite.addAll(MainApplication.getFootballEvent.getAllTeamsData(leagueName)
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ result -> mView.onSuccess(result.events) },
//                        { error -> mView.onFailed(error.localizedMessage) },
//                        { EspressoIdlingResource.decrements() }))
//    }


}