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

    fun getData() {
        EspressoIdlingResource.increments()
        composite.add(MainApplication.getFootballEvent.getPastEventData()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> mView.onSuccess(result.events) },
                        { error -> mView.onFailed(error.localizedMessage) },
                        {EspressoIdlingResource.decrements()}))

    }


    override fun onCreateView(view: View) {
        mView.initView(view)
    }




}