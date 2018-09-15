package com.example.junemon.kotlinnetworking.feature.mainscreen

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.databases.DatabaseLeagueModel
import com.example.junemon.kotlinnetworking.databases.database
import com.example.junemon.kotlinnetworking.home.HomeActivity
import com.example.junemon.kotlinnetworking.model.AllLeagueModel
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MainScreen : AppCompatActivity(), MainScreenView {
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        presenter.onCreate(this)
        presenter.addDatabaseLeague()
        presenter.showDbLeague()

        swipeMainScreen.setOnRefreshListener {
            presenter.showDbLeague()
            swipeMainScreen.isRefreshing = false
        }
    }

    override fun onSuccess(data: List<AllLeagueModel.League>) {
        database.use {
            val result = select(DatabaseLeagueModel.TABLE_LEAGUE)
                    .whereArgs("(ID_LEAGUE = {data})",
                            "data" to data.get(0).idLeague!!)
            val favorite = result.parseList(classParser<DatabaseLeagueModel>())
            if (favorite.isEmpty()) {
                Observable.fromIterable(data).subscribe { results ->
                    try {
                        database.use {
                            insert(DatabaseLeagueModel.TABLE_LEAGUE,
                                    DatabaseLeagueModel.STR_LEAGUE_ALTERNATE to results.strLeagueAlternate,
                                    DatabaseLeagueModel.STR_LEAGUE to results.strLeague,
                                    DatabaseLeagueModel.STR_SPORT to results.strSport,
                                    DatabaseLeagueModel.ID_LEAGUE to results.idLeague
                            )
                        }
                    } catch (e: SQLiteConstraintException) {
                    }
                }
            }
        }
    }

    override fun onShowDbData(data: List<DatabaseLeagueModel>) {
        rvLeague.layoutManager = LinearLayoutManager(this)
        rvLeague.isNestedScrollingEnabled = false
        rvLeague.adapter = MainScreenAdapter(ctx, data) {
            Observable.just(it).subscribe { results ->
                alert("Ingin Melihat Data Liga ${it.strLeague} ?") {
                    yesButton { startActivity<HomeActivity>("keys" to results) }
                    noButton { }
                }.show()
            }
        }
    }


    override fun onFailed(message: String) {

    }

    override fun initView() {

    }
}