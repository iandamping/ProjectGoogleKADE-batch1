package com.example.junemon.kotlinnetworking.feature.nextmatch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.model.MainModelNextMatch
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_nextmatch.*

class NextMatchAdapter(val ctx: Context?, val listData: List<MainModelNextMatch.Event>, val listener: (MainModelNextMatch.Event) -> Unit)
    : RecyclerView.Adapter<NextMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_nextmatch, parent, false))
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(listData.get(position), listener)
    }


    class ViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindView(data: MainModelNextMatch.Event, listener: (MainModelNextMatch.Event) -> Unit) {
            tvDateFootbalNextMatch.text = data.dateEvent
            tvHomeNextMatch.text = data.strHomeTeam
            tvHomeScoreNextMatch.text = data.intHomeScore
            tvAwayScoreNextMatch.text = data.intAwayScore
            tvAwayNextMatch.text = data.strAwayTeam
            itemView.setOnClickListener { listener((data)) }
        }
    }
}