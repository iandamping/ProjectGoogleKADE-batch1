package com.example.junemon.kotlinnetworking.databases

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DatabaseLeagueModel(val id: Long?, val idLeague: String?, val strLeague: String?,
                               val strSport: String?, val strLeagueAlternate: String?) : Parcelable {
    companion object {
        const val TABLE_LEAGUE: String = "TABLE_LEAGUE"
        const val ID: String = "ID_"
        const val ID_LEAGUE: String = "ID_LEAGUE"
        const val STR_LEAGUE: String = "STR_LEAGUE"
        const val STR_SPORT: String = "STR_SPORT"
        const val STR_LEAGUE_ALTERNATE: String = "STR_LEAGUE_ALTERNATE"
    }
}