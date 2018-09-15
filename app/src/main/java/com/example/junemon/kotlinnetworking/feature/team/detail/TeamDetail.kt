package com.example.junemon.kotlinnetworking.feature.team.detail

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.R.drawable.ic_add_to_favorites
import com.example.junemon.kotlinnetworking.R.drawable.ic_added_to_favorites
import com.example.junemon.kotlinnetworking.R.id.add_to_favorite
import com.example.junemon.kotlinnetworking.databases.DatabasesTeamModel
import com.example.junemon.kotlinnetworking.databases.database
import com.example.junemon.kotlinnetworking.helper.PlayerAdapterFragment
import com.example.junemon.kotlinnetworking.model.TeamModel
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.player_pager.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetail : AppCompatActivity() {
    lateinit var data: TeamModel.Team
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_pager)
        val intent: Intent = getIntent()
        data = intent.getParcelableExtra(Integer.toString(R.string.data_all_team))
        Picasso.get().load(data.strTeamBadge).into(imTeamOverview)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        tabPlayerPage.setupWithViewPager(vpPlayerPage)
        if (vpPlayerPage != null) {
            vpPlayerPage.adapter = buildPlayerAdapter()
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(DatabasesTeamModel.TABLE_TEAM)
                    .whereArgs("(ID_TEAM = {data})",
                            "data" to data.idTeam!!)
            val favorite = result.parseList(classParser<DatabasesTeamModel>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFav(data)

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(DatabasesTeamModel.TABLE_TEAM, "(ID_TEAM = {data})",
                        "data" to data.idTeam!!)
            }
        } catch (e: SQLiteConstraintException) {
        }
    }


    private fun setFavorite() {
        favoriteState()
        Observable.just(isFavorite).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { results ->
                    if (results) {
                        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
                    } else {
                        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
                    }
                }
    }

    private fun addToFav(data: TeamModel.Team) {
        try {
            database.use {
                insert(DatabasesTeamModel.TABLE_TEAM,
                        DatabasesTeamModel.ID_TEAM to data.idTeam,
                        DatabasesTeamModel.STR_TEAM_BADGE to data.strTeamBadge,
                        DatabasesTeamModel.STR_TEAM to data.strTeam,
                        DatabasesTeamModel.STR_DESCRIPTION to data.strDescriptionEN
                )
            }
        } catch (e: SQLiteConstraintException) {
        }

    }

    fun buildPlayerAdapter(): PagerAdapter {
        return PlayerAdapterFragment(supportFragmentManager, data)
    }
}