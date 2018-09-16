package com.example.junemon.kotlinnetworking.feature.mainscreen

import com.example.junemon.kotlinnetworking.BuildConfig
import com.example.junemon.kotlinnetworking.MainApplication
import com.example.junemon.kotlinnetworking.databases.DatabaseLeagueModel
import com.example.junemon.kotlinnetworking.databases.DatabaseOpenHelper
import com.example.junemon.kotlinnetworking.model.AllLeagueModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class MainScreenTest {
    @Mock
    private lateinit var mView: MainScreenView
    @Mock
    lateinit var database: DatabaseOpenHelper
    @Mock
    private lateinit var presenter: MainPresenter
    @Mock
    private lateinit var data: AllLeagueModel.League
    @Mock
    private lateinit var composite: CompositeDisposable

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(mView)
        database = DatabaseOpenHelper(RuntimeEnvironment.application)
        data = AllLeagueModel.League()
        composite = CompositeDisposable()
    }

    @Test
    fun getDatas() {
        composite.addAll(MainApplication.getFootballEvent.getAllLeague()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ results -> data },
                        { error -> mView.onFailed(error.localizedMessage) }))
    }

    @Test
    fun createDB() {
        database.use {
            createTable(DatabaseLeagueModel.TABLE_LEAGUE, true,
                    DatabaseLeagueModel.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                    DatabaseLeagueModel.ID_LEAGUE to TEXT,
                    DatabaseLeagueModel.STR_LEAGUE to TEXT,
                    DatabaseLeagueModel.STR_SPORT to TEXT,
                    DatabaseLeagueModel.STR_LEAGUE_ALTERNATE to TEXT)
        }
    }

    @Test
    fun addToFav() {
        database.use {
            insert(DatabaseLeagueModel.TABLE_LEAGUE,
                    DatabaseLeagueModel.STR_LEAGUE_ALTERNATE to data.strLeagueAlternate,
                    DatabaseLeagueModel.STR_LEAGUE to data.strLeague,
                    DatabaseLeagueModel.STR_SPORT to data.strSport,
                    DatabaseLeagueModel.ID_LEAGUE to data.idLeague
            )


        }
    }
}