package com.example.junemon.kotlinnetworking.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class AllLeagueModel(val leagues: List<League>) {
    @Parcelize
    class League(var idLeague: String?, var strLeague: String?,
                 var strSport: String?, var strLeagueAlternate: String?) : Parcelable {

    }

}