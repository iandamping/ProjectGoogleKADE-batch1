package com.example.junemon.kotlinnetworking.feature.mainscreen

import android.content.Context
import com.example.junemon.kotlinnetworking.MainApplication
import com.example.junemon.kotlinnetworking.base.BasePresenter
import com.example.junemon.kotlinnetworking.databases.DatabaseLeagueModel
import com.example.junemon.kotlinnetworking.databases.database
import com.example.junemon.kotlinnetworking.helper.EspressoIdlingResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class MainPresenter(var mView: MainScreenView) : BasePresenter {
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

    fun showDbLeague() {
        EspressoIdlingResource.increments()
        ctx?.database?.use {
            val result = select(DatabaseLeagueModel.TABLE_LEAGUE)
            val favorite = result.parseList(classParser<DatabaseLeagueModel>())
            mView.onShowDbData(favorite)
            EspressoIdlingResource.decrements()
        }
    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }
}