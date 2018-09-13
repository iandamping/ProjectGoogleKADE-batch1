package com.example.junemon.kotlinnetworking.feature.lastmatch

import com.example.junemon.kotlinnetworking.MainApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LastMatchFragmentPresenterTest() {
    @Mock
    private lateinit var mView:LastMatchFragmentView
    @Mock
    private lateinit var composite: CompositeDisposable

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        composite = CompositeDisposable()

    }

    @Test
    fun getData() {
        composite.add(MainApplication.getFootballEvent.getPastEventData()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> mView.onSuccess(result.events) },
                        { error -> mView.onFailed(error.localizedMessage) }))
    }

}