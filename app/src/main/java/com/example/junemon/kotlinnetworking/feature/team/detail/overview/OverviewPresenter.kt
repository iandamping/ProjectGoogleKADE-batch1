package com.example.junemon.kotlinnetworking.feature.team.detail.overview

import android.content.Context
import android.view.View
import com.example.junemon.kotlinnetworking.MainApplication
import com.example.junemon.kotlinnetworking.base.BaseFragmentPresenter
import com.example.junemon.kotlinnetworking.helper.EspressoIdlingResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class OverviewPresenter(var mView: OverviewView) : BaseFragmentPresenter {
    private var ctx: Context? = null
    lateinit var composite: CompositeDisposable
    override fun onAttach(context: Context?) {
        this.ctx = context
        composite = CompositeDisposable()
    }

    override fun onCreateView(view: View) {
        mView.initView(view)
    }

    fun getListTeam(data: String = "133604") {
        EspressoIdlingResource.increments()
        composite.addAll(MainApplication.getFootballEvent.getTeamDetail(data).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe({ results -> mView.onSuccessData(results.teams) },
                        { error -> mView.onFail(error.localizedMessage) },
                        { EspressoIdlingResource.decrements() }))
    }
}