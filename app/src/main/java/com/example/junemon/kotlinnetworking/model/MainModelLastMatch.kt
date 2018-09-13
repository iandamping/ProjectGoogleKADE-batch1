package com.example.junemon.kotlinnetworking.model

import android.os.Parcelable
import com.example.junemon.kotlinnetworking.R
import kotlinx.android.parcel.Parcelize


class MainModelLastMatch(val events: List<Event>) {

    @Parcelize
    class Event(
            var strHomeTeam: String? = Integer.toString(R.string.no_data),
            var strAwayTeam: String? = Integer.toString(R.string.no_data),
            var intHomeScore: String? = Integer.toString(R.string.no_data),
            var intAwayScore: String? = Integer.toString(R.string.no_data),
            var strHomeGoalDetails: String? = Integer.toString(R.string.no_data),
            var strHomeLineupGoalkeeper: String? = Integer.toString(R.string.no_data),
            var strHomeLineupDefense: String? = Integer.toString(R.string.no_data),
            var strHomeLineupMidfield: String? = Integer.toString(R.string.no_data),
            var strHomeLineupForward: String? = Integer.toString(R.string.no_data),
            var strAwayGoalDetails: String? = Integer.toString(R.string.no_data),
            var strAwayLineupGoalkeeper: String? = Integer.toString(R.string.no_data),
            var strAwayLineupDefense: String? = Integer.toString(R.string.no_data),
            var strAwayLineupMidfield: String? = Integer.toString(R.string.no_data),
            var strAwayLineupForward: String? = Integer.toString(R.string.no_data),
            var dateEvent: String? = Integer.toString(R.string.no_data),
            var idHomeTeam: String? = Integer.toString(R.string.no_data),
            var idAwayTeam: String? = Integer.toString(R.string.no_data)
    ) : Parcelable
}
