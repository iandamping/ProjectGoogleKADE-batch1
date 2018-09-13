package com.example.junemon.kotlinnetworking.feature.favorites.detail

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.databases.DatabaseModel
import com.example.junemon.kotlinnetworking.databases.database
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select

class FavDetailActivity : AppCompatActivity(), FavDetailView {
    lateinit var getdata: DatabaseModel
    lateinit var presenter: FavDetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_favorite)

        val intent: Intent = getIntent()
        getdata = intent.getParcelableExtra(Integer.toString(R.string.parcel_data_key))
        presenter = FavDetailPresenter(this)
        presenter.onCreate(this)
        presenter.getData(getdata)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setFavorite() {
        favoriteState()
        Observable.just(isFavorite).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { results ->
                    if (results) {
                        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
                    } else {
                        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
                    }
                }
    }

    override fun successGetData(data: DatabaseModel) {
        tvDetailDateFav.text = data.teamAwayScore
        tvHomeTeamDetailFav.text = data.homeMidField
        tvAwayTeamDetailFav.text = data.awayForward
        tvAwayMidFieldFav.text = data.teamHomeScore
        tvHomeMidFieldFav.text = data.homeDefense
        tvAwayDefenseFav.text = data.teamHomeId
        tvHomeDefenseFav.text = data.homeGoalDetail
        tvAwayGoalKeeperFav.text = data.teamHomeName
        tvHomeGoalKeeperFav.text = data.homeGoalKeeper
        tvAwayForwardFav.text = data.teamAwayIdName
        tvHomeForwardFav.text = data.awayGoalDetail
        tvAwayGoalsFav.text = data.teamAwayName
        tvHomeGoalsFav.text = data.awayGoalKeeper
        Picasso.get().load(data.teamHomeBadge).into(imgAwayFav)
        Picasso.get().load(data.teamAwayBadge).into(imgHomeFav)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(DatabaseModel.TABLE_FAVORITE)
                    .whereArgs("(TEAM_HOME_NAME = {data})",
                            "data" to getdata.homeMidField!!)
            val favorite = result.parseList(classParser<DatabaseModel>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(DatabaseModel.TABLE_FAVORITE, "(TEAM_HOME_NAME = {data})",
                        "data" to getdata.homeMidField!!)
            }
        } catch (e: SQLiteConstraintException) {
        }
    }

    override fun failGetData(message: String) {
    }

    override fun initView() {

    }

}
