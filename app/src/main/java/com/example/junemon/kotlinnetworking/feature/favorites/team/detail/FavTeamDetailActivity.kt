package com.example.junemon.kotlinnetworking.feature.favorites.team.detail

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.databases.DatabasesTeamModel
import com.example.junemon.kotlinnetworking.databases.database
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_fav_team_page.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select

class FavTeamDetailActivity : AppCompatActivity(), FavTeamDetailView {

    lateinit var getdata: DatabasesTeamModel
    lateinit var presenter: FavTeamDetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav_team_page)

        val intent: Intent = getIntent()
        getdata = intent.getParcelableExtra(Integer.toString(R.string.detail_all_team))
        presenter = FavTeamDetailPresenter(this)
        presenter.onCreate(this)
        presenter.getData(getdata)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

    private fun favoriteState() {
        database.use {
            val result = select(DatabasesTeamModel.TABLE_TEAM)
                    .whereArgs("(ID_TEAM = {data})",
                            "data" to getdata.strDescriptionEN!!)
            val favorite = result.parseList(classParser<DatabasesTeamModel>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(DatabasesTeamModel.TABLE_TEAM, "(ID_TEAM = {data})",
                        "data" to getdata.strDescriptionEN!!)
            }
        } catch (e: SQLiteConstraintException) {
        }
    }

    override fun successGetData(data: DatabasesTeamModel) {
        Picasso.get().load(data.strDescriptionEN).into(imTeamOverview)
        tvTeamFavDescription.text = data.strTeamBadge
    }

    override fun failGetData(message: String) {
    }

    override fun initView() {
    }
}