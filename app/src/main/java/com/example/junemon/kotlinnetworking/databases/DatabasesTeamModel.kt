package com.example.junemon.kotlinnetworking.databases

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DatabasesTeamModel(val id: Long?, var strTeam: String?, var strTeamBadge: String?,
                         var idTeam: String?, var strDescriptionEN: String?) : Parcelable {
    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val STR_TEAM: String = "STR_TEAM"
        const val STR_TEAM_BADGE: String = "STR_TEAM_BADGE"
        const val STR_DESCRIPTION: String = "STR_DESCRIPTION"
    }
}