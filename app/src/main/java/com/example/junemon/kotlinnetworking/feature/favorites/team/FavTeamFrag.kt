package com.example.junemon.kotlinnetworking.feature.favorites.team

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.databases.DatabasesTeamModel
import com.example.junemon.kotlinnetworking.feature.favorites.team.detail.FavTeamDetailActivity
import kotlinx.android.synthetic.main.activity_team_favorite.*
import kotlinx.android.synthetic.main.activity_team_favorite.view.*
import org.jetbrains.anko.support.v4.intentFor

class FavTeamFrag : Fragment(), FavTeamView {
    lateinit var viewsd: View
    var presenter: FavTeamPresenter = FavTeamPresenter(this)
    var ctx: Context? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.ctx = context
        presenter.onAttach(ctx)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var views: View = inflater.inflate(R.layout.activity_team_favorite, container, false)
        presenter.onCreateView(views)
        views.swipeRefFavTeam.setOnRefreshListener {
            presenter.showFav()
            swipeRefFavTeam.isRefreshing = false
        }
        return views
    }

    override fun onSuccesGetData(data: List<DatabasesTeamModel>) {
        viewsd.rvFavAllTeam.layoutManager = LinearLayoutManager(ctx)

        viewsd.rvFavAllTeam.adapter = FavTeamAdapter(ctx, data) {
            startActivity(intentFor<FavTeamDetailActivity>(Integer.toString(R.string.detail_all_team) to it))
        }

        viewsd.rvFavAllTeam.adapter.notifyDataSetChanged()
    }

    override fun onFailedGetData(message: String) {
    }

    override fun initView(view: View) {
        viewsd = view
        presenter.showFav()
    }
}