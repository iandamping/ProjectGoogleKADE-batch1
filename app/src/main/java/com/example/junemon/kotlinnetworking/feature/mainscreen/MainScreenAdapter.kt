package com.example.junemon.kotlinnetworking.feature.mainscreen

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.databases.DatabaseLeagueModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_mainscreen.*

class MainScreenAdapter(val ctx: Context?, val listData: List<DatabaseLeagueModel>, val listener: (DatabaseLeagueModel) -> Unit)
    : RecyclerView.Adapter<MainScreenAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_mainscreen, parent, false))
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(listData.get(position), listener)
    }


    class ViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindViews(data: DatabaseLeagueModel, listener: (DatabaseLeagueModel) -> Unit) {
            tvLeague.text = data.strLeague
            itemView.setOnClickListener {
                listener((data))
            }
        }
    }
}