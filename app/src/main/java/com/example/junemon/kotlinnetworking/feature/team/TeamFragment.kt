package com.example.junemon.kotlinnetworking.feature.team

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.feature.team.detail.TeamDetail
import com.example.junemon.kotlinnetworking.model.TeamModel
import kotlinx.android.synthetic.main.activity_all_team.*
import kotlinx.android.synthetic.main.activity_all_team.view.*
import org.jetbrains.anko.support.v4.intentFor

class TeamFragment : Fragment(), TeamView {
    var presenter: TeamPresenter = TeamPresenter(this)
    var ctx: Context? = null
    var TYPE_TEAM: String? = "type_team"
    lateinit var typeTeams: String

    fun newInstance(type: String?): TeamFragment {
        val bundle = Bundle()
        val fragment = TeamFragment()
        bundle.putString(TYPE_TEAM, type)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        typeTeams = args?.getString(TYPE_TEAM) ?: "English Premier League"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.ctx = context
        presenter.onAttach(ctx)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var views: View = inflater.inflate(R.layout.activity_all_team, container, false)
        presenter.getListTeam(typeTeams)
        views.swipesRefreshTeam.setOnRefreshListener {
            presenter.getListTeam(typeTeams)
            swipesRefreshTeam.isRefreshing = false
        }
        return views
    }

    override fun onSuccessData(data: List<TeamModel.Team>) {
        rvAllTeam.layoutManager = LinearLayoutManager(ctx)
        rvAllTeam.adapter = TeamAdapter(ctx, data) {
            startActivity(intentFor<TeamDetail>("KeysTeam" to it))
        }
    }

    override fun onFail(message: String) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
    }

    override fun initView(view: View) {
        presenter.getListTeam(typeTeams)
    }
}