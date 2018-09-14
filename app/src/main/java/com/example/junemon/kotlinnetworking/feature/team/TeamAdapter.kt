package com.example.junemon.kotlinnetworking.feature.team

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.model.TeamModel
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_team.*

class TeamAdapter(var ctx: Context?, var listData: List<TeamModel.Team>, val listener: (TeamModel.Team) -> Unit)
    : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_list_team, parent, false))
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(listData.get(position), listener)
    }


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindViews(item: TeamModel.Team, listener: (TeamModel.Team) -> Unit) {
            Picasso.get().load(item.strTeamBadge).into(ivAllTeam)
            tvAllTeam.text = item.strTeam
            itemView.setOnClickListener { listener((item)) }
        }
    }
}