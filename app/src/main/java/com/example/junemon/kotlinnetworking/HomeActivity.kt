package com.example.junemon.kotlinnetworking

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.junemon.kotlinnetworking.databases.DatabaseLeagueModel
import com.example.junemon.kotlinnetworking.databases.database
import com.example.junemon.kotlinnetworking.feature.favorites.FavFragment
import com.example.junemon.kotlinnetworking.feature.lastmatch.LastMatchFragment
import com.example.junemon.kotlinnetworking.feature.nextmatch.NextMatchFragment
import com.example.junemon.kotlinnetworking.model.AllLeagueModel
import io.reactivex.Observable
import kotlinx.android.synthetic.main.homeactivity.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class HomeActivity : AppCompatActivity(), HomeView {


    lateinit var presenter: HomePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter(this)
        presenter.onCreate(this)
        presenter.addDatabaseLeague()
        setContentView(R.layout.homeactivity)
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.lastMatches -> {
                    loadMainFragment(savedInstanceState)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nextMatches -> {
                    loadNextMatchFragment(savedInstanceState)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.favourites -> {
                    loadFavoritesFragment(savedInstanceState)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
        bottom_navigation.selectedItemId = R.id.lastMatches
    }

    private fun loadMainFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MainPager(), FavFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadNextMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadPreviousMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavFragment(), FavFragment::class.java.simpleName)
                    .commit()
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

    override fun onFailed(message: String) {
    }

    override fun initView() {
    }

}