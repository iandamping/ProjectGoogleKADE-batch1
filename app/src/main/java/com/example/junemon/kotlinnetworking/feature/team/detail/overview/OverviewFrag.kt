package com.example.junemon.kotlinnetworking.feature.team.detail.overview

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.model.TeamDetailModel
import kotlinx.android.synthetic.main.activity_overview.*

class OverviewFrag : Fragment(), OverviewView {
    var presenter: OverviewPresenter = OverviewPresenter(this)
    var ctx: Context? = null
    var TYPE_TEAM: String? = "type_team"
    lateinit var typeTeams: String

    fun newInstance(type: String?): OverviewFrag {
        val bundle = Bundle()
        val fragment = OverviewFrag()
        bundle.putString(TYPE_TEAM, type)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        typeTeams = args?.getString(TYPE_TEAM) ?: "133604"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.ctx = context
        presenter.onAttach(ctx)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var views: View = LayoutInflater.from(ctx).inflate(R.layout.activity_overview, container, false)
        presenter.getListTeam(typeTeams)

        return views
    }

    override fun onSuccessData(data: List<TeamDetailModel.Team>) {
        rvOverView.layoutManager = LinearLayoutManager(ctx)
        rvOverView.adapter = OverviewAdapter(ctx, data) {

        }
    }

    override fun onFail(message: String) {
    }

    override fun initView(view: View) {
        presenter.getListTeam(typeTeams)
    }
}