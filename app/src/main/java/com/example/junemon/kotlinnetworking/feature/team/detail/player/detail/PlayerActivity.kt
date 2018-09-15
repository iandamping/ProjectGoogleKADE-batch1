package com.example.junemon.kotlinnetworking.feature.team.detail.player.detail

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.model.AllPlayer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*

class PlayerActivity : AppCompatActivity(), PlayerDetailView {


    lateinit var presenter: PlayerDetailPresenter
    lateinit var data: AllPlayer.Player
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        presenter = PlayerDetailPresenter(this)
        presenter.onCreate(this)

        val intent: Intent = getIntent()
        data = intent.getParcelableExtra(Integer.toString(R.string.player_detail_frag))
        presenter.getPlayerDetail(data)
    }

    override fun onGetData(allData: AllPlayer.Player) {
        Picasso.get().load(data.strThumb).into(imPlayerDetail)
        tvPlayerDetail.text = data.strDescriptionEN
        tvPlayerHeight.text = data.strHeight
        tvPlayerWeight.text = data.strWeight
    }


    override fun failGetData(message: String) {
        Toast.makeText(this@PlayerActivity, message, Toast.LENGTH_SHORT).show()

    }

    override fun initView() {
    }
}