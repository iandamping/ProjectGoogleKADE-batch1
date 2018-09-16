package com.example.junemon.kotlinnetworking.feature.search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.model.MainModelLastMatch
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_search.*

class SearchLastAdapter(val ctx: Context?, val listData: List<MainModelLastMatch.Event>, val listener: (MainModelLastMatch.Event) -> Unit)
    : RecyclerView.Adapter<SearchLastAdapter.ViewHolder>() {


    override fun getItemCount(): Int = listData.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_search, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (listData != null) {
            holder.bindData(listData.get(position), listener)
        }
    }


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(item: MainModelLastMatch.Event, listener: (MainModelLastMatch.Event) -> Unit) {
            tvDateFootbalMatchSearch.text = item.dateEvent
            tvHomeTeamSearch.text = item.strHomeTeam
            tvAwayTeamSearch.text = item.strAwayTeam
            tvHomeScoreSearch.text = item.intHomeScore
            tvAwayScoreSearch.text = item.intAwayScore
            itemView.setOnClickListener {
                listener((item))
            }

        }
    }
}