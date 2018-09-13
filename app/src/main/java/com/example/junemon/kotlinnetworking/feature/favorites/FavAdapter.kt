package com.example.junemon.kotlinnetworking.feature.favorites

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.databases.DatabaseModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_favorite.*

class FavAdapter(val ctx: Context?, val listData: List<DatabaseModel>, val listener: (DatabaseModel) -> Unit) :
        RecyclerView.Adapter<FavAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_favorite, parent, false))
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (listData != null) {
            holder.bindView(listData.get(position), listener)
        }
    }


    class ViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindView(item: DatabaseModel, listener: (DatabaseModel) -> Unit) {
            tvDateFootbalMatchFav.text = item.teamAwayScore
            tvHomeTeamFav.text = item.homeMidField
            tvHomeScoreFav.text = item.awayMidField
            tvAwayTeamFav.text = item.awayForward
            tvAwayScoreFav.text = item.dateEvent
            itemView.setOnClickListener {
                listener((item))
            }
        }
    }
}
