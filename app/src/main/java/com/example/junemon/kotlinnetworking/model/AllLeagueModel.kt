package com.example.junemon.kotlinnetworking.model

import android.os.Parcelable
import com.example.junemon.kotlinnetworking.R
import kotlinx.android.parcel.Parcelize

class AllLeagueModel(val leagues: List<League>) {
    @Parcelize
    class League(var idLeague: String? =Integer.toString(R.string.no_data), var strLeague: String? =Integer.toString(R.string.no_data),
                 var strSport: String?=Integer.toString(R.string.no_data), var strLeagueAlternate: String?=Integer.toString(R.string.no_data)) : Parcelable

}