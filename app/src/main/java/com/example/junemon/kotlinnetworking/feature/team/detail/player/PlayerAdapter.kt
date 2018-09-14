package com.example.junemon.kotlinnetworking.feature.team.detail.player

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.model.AllPlayer
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_all_player.*

class PlayerAdapter(var ctx: Context?, var listData: List<AllPlayer.Player>, val listener: (AllPlayer.Player) -> Unit)
    : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerAdapter.ViewHolder {
        return PlayerAdapter.ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_all_player, parent, false))
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: PlayerAdapter.ViewHolder, position: Int) {
        holder.bindViews(listData.get(position), listener)
    }


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindViews(item: AllPlayer.Player, listener: (AllPlayer.Player) -> Unit) {
            Picasso.get().load(item.strThumb).into(ivAllPlayer)
            tvPlayerName.text = item.strPlayer
            tvPlayerPosition.text = item.strPosition

            itemView.setOnClickListener { listener((item)) }
        }
    }
}