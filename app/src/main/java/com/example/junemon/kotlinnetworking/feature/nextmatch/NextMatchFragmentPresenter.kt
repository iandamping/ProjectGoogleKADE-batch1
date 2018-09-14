package com.example.junemon.kotlinnetworking.feature.nextmatch

import android.content.Context
import android.view.View
import com.example.junemon.kotlinnetworking.MainApplication
import com.example.junemon.kotlinnetworking.base.BaseFragmentPresenter
import com.example.junemon.kotlinnetworking.databases.DatabaseLeagueModel
import com.example.junemon.kotlinnetworking.databases.database
import com.example.junemon.kotlinnetworking.helper.EspressoIdlingResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class NextMatchFragmentPresenter(var mView: NextMatchFragmentView) : BaseFragmentPresenter {


    private var ctx: Context? = null
    lateinit var composite: CompositeDisposable

    override fun onAttach(context: Context?) {
        this.ctx = context
        composite = CompositeDisposable()
    }

    fun getData(data:String ="4328") {
        composite.add(MainApplication.getFootballEvent.getNextEventData(data)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> mView.onSuccess(result.events) },
                        { error -> mView.onFailed(error.localizedMessage) }))

    }

    override fun onCreateView(view: View) {
        mView.initView(view)
    }



}