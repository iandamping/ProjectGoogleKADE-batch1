package com.example.junemon.kotlinnetworking

import android.content.Context
import com.example.junemon.kotlinnetworking.base.BasePresenter
import com.example.junemon.kotlinnetworking.helper.EspressoIdlingResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(var mView: HomeView) : BasePresenter {
    private var ctx: Context? = null
    lateinit var composite: CompositeDisposable

    override fun getContext(): Context? {
        return ctx
    }

    override fun onCreate(context: Context) {
        this.ctx = context
        composite = CompositeDisposable()
        mView.initView()
    }

    fun addDatabaseLeague() {
        EspressoIdlingResource.increments()
        composite.addAll(MainApplication.getFootballEvent.getAllLeague()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ results -> mView.onSuccess(results.leagues) },
                        { error -> mView.onFailed(error.localizedMessage) },
                        { EspressoIdlingResource.decrements() }))
    }

    override fun onPause() {
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}