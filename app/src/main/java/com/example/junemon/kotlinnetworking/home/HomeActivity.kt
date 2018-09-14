package com.example.junemon.kotlinnetworking.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.junemon.kotlinnetworking.MainPager
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.databases.DatabaseLeagueModel
import com.example.junemon.kotlinnetworking.feature.favorites.FavFragment
import com.example.junemon.kotlinnetworking.feature.lastmatch.LastMatchFragment
import com.example.junemon.kotlinnetworking.feature.nextmatch.NextMatchFragment
import com.example.junemon.kotlinnetworking.model.MainModelLastMatch
import kotlinx.android.synthetic.main.homeactivity.*

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
                    .replace(R.id.main_container, MainPager().newInstance(data.idLeague), FavFragment::class.java.simpleName)
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

}