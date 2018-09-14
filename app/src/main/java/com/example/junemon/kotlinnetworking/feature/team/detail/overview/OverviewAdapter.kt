package com.example.junemon.kotlinnetworking.feature.team.detail.overview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.model.TeamDetailModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_overview.*

class OverviewAdapter(var ctx: Context?, var listData: List<TeamDetailModel.Team>, val listener: (TeamDetailModel.Team) -> Unit)
    : RecyclerView.Adapter<OverviewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewAdapter.ViewHolder {
        return OverviewAdapter.ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_overview, parent, false))
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: OverviewAdapter.ViewHolder, position: Int) {
        holder.bindViews(listData.get(position), listener)
    }


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindViews(item: TeamDetailModel.Team, listener: (TeamDetailModel.Team) -> Unit) {
            tvOverView.text = item.strDescriptionEN
            itemView.setOnClickListener { listener((item)) }
        }
    }
}