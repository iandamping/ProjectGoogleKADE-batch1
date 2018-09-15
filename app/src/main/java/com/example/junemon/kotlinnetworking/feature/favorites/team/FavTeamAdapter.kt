package com.example.junemon.kotlinnetworking.feature.favorites.team

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.databases.DatabasesTeamModel
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_fav_team.*

class FavTeamAdapter(var ctx: Context?, var listData: List<DatabasesTeamModel>, val listener: (DatabasesTeamModel) -> Unit)
    : RecyclerView.Adapter<FavTeamAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_fav_team, parent, false))
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(listData.get(position), listener)
    }


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindViews(item: DatabasesTeamModel, listener: (DatabasesTeamModel) -> Unit) {
            Picasso.get().load(item.strDescriptionEN).into(ivAllFavTeam)
            tvAllFavTeam.text = item.idTeam
            itemView.setOnClickListener { listener((item)) }
        }
    }
}