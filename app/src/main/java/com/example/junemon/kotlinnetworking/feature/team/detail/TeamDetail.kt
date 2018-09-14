package com.example.junemon.kotlinnetworking.feature.team.detail

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.helper.PlayerAdapterFragment
import com.example.junemon.kotlinnetworking.model.TeamModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.player_pager.*

class TeamDetail : AppCompatActivity() {
    lateinit var data: TeamModel.Team
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_pager)
        val intent: Intent = getIntent()
        data = intent.getParcelableExtra("KeysTeam")
        Picasso.get().load(data.strTeamBadge).into(imTeamOverview)

        tabPlayerPage.setupWithViewPager(vpPlayerPage)
        if (vpPlayerPage != null) {
            vpPlayerPage.adapter = buildPlayerAdapter()
        }
    }

    fun buildPlayerAdapter(): PagerAdapter {
        return PlayerAdapterFragment(supportFragmentManager, data)
    }
}