package com.example.junemon.kotlinnetworking.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.junemon.kotlinnetworking.MainPager
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.databases.DatabaseLeagueModel
import com.example.junemon.kotlinnetworking.feature.favorites.FavPager
import com.example.junemon.kotlinnetworking.feature.lastmatch.LastMatchFragment
import com.example.junemon.kotlinnetworking.feature.search.SearchActivity
import com.example.junemon.kotlinnetworking.feature.team.TeamFragment
import kotlinx.android.synthetic.main.homeactivity.*
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity() {
    lateinit var data: DatabaseLeagueModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homeactivity)
        val intent: Intent = getIntent()
        data = intent.getParcelableExtra("keys")

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.lastMatches -> {
                    loadMainFragment(savedInstanceState)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.listTeams -> {
                    loadTeamFragment(savedInstanceState)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_search -> {
                startActivity<SearchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadMainFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MainPager().newInstance(data.idLeague), MainPager::class.java.simpleName)
                    .commit()
        }
    }


    private fun loadTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamFragment().newInstance(data.strLeague), LastMatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavPager(), FavPager::class.java.simpleName)
                    .commit()
        }
    }

}