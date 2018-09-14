package com.example.junemon.kotlinnetworking.feature.team.detail.player

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.model.AllPlayer
import kotlinx.android.synthetic.main.activity_player_all.*

class PlayerFrag : Fragment(), PlayerView {
    var presenter: PlayerPresenter = PlayerPresenter(this)
    var ctx: Context? = null
    var TYPE_TEAM: String? = "type_team"
    lateinit var typeTeams: String

    fun newInstance(type: String?): PlayerFrag {
        val bundle = Bundle()
        val fragment = PlayerFrag()
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
        var views: View = LayoutInflater.from(ctx).inflate(R.layout.activity_player_all, container, false)
        presenter.getAllPlayer(typeTeams)

        return views
    }

    override fun onSuccessData(data: List<AllPlayer.Player>) {
        rvAllPlayer.layoutManager = LinearLayoutManager(ctx)
        rvAllPlayer.adapter = PlayerAdapter(ctx, data) {

        }
    }

    override fun onFail(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView(view: View) {
        presenter.getAllPlayer(typeTeams)
    }
}