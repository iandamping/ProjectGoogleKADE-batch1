package com.example.junemon.kotlinnetworking.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class TeamModel(val teams: List<Team>) {
    @Parcelize
    class Team(var strTeam: String?, var strTeamBadge: String?, var idTeam: String?,
               var strDescriptionEN: String?, var idLeague: String?) : Parcelable
}