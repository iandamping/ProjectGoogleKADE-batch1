package com.example.junemon.kotlinnetworking.feature.search

import com.example.junemon.kotlinnetworking.MainApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SearchPresenterTest {
    @Mock
    private lateinit var composite: CompositeDisposable
    @Mock
    private lateinit var mView: SerachView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        composite = CompositeDisposable()

    }

    @Test
    fun getLastData() {
        composite.addAll(MainApplication.getFootballEvent.getSearchPastEventData("4480")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> mView.getLastData(result.events) },
                        { error -> mView.onFailed("Tidak Ada Data Pertandingan") }))

    }

    @Test
    fun getNextData() {
        composite.add(MainApplication.getFootballEvent.getSearchNextEventData("4480")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> mView.getNextData(result.events) },
                        { error -> mView.onFailed("Tidak Ada Data Pertandingan") }))

    }

    @Test
    fun getAllTeamDetails() {
        composite.add(MainApplication.getFootballEvent.getTeamSearchDetails("4480")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    getLastData()
                    getNextData()
                },
                        { error -> mView.onFailed("Team Tidak Ada") }))
    }
}