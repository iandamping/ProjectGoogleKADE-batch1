package com.example.junemon.kotlinnetworking.databases

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DatabaseModel(val id: Long?, val teamHomeId: String?, val teamAwayIdName: String?, val teamHomeName: String?,
                         val teamAwayName: String?, val teamHomeScore: String?, val teamAwayScore: String?, val homeGoalDetail: String?,
                         val awayGoalDetail: String?, val homeGoalKeeper: String?, val awayGoalKeeper: String?, val homeDefense: String?,
                         val awayDefense: String?, val homeMidField: String?, val awayMidField: String?, val homeForward: String?,
                         val awayForward: String?, val dateEvent: String?, val teamHomeBadge: String?, val teamAwayBadge: String?) : Parcelable {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val TEAM_HOME_ID: String = "TEAM_HOME_ID"
        const val TEAM_AWAY_ID: String = "TEAM_AWAY_ID"
        const val TEAM_HOME_NAME: String = "TEAM_HOME_NAME"
        const val TEAM_AWAY_NAME: String = "TEAM_AWAY_NAME"
        const val TEAM_HOME_SCORE: String = "TEAM_HOME_SCORE"
        const val TEAM_AWAY_SCORE: String = "TEAM_AWAY_SCORE"
        const val HOME_GOAL_DETAILS: String = "HOME_GOAL_DETAILS"
        const val AWAY_GOAL_DETAILS: String = "AWAY_GOAL_DETAILS"
        const val HOME_GOALKEPEER: String = "HOME_GOALKEPEER"
        const val AWAY_GOALKEPEER: String = "AWAY_GOALKEPEER"
        const val HOME_DEFENSE: String = "HOME_DEFENSE"
        const val AWAY_DEFENSE: String = "AWAY_DEFENSE"
        const val HOME_MIDFIELD: String = "HOME_MIDFIELD"
        const val AWAY_MIDFIELD: String = "AWAY_MIDFIELD"
        const val HOME_FORWARD: String = "HOME_FORWARD"
        const val AWAY_FORWARD: String = "AWAY_FORWARD"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val TEAM_AWAY_BADGE: String = "TEAM_AWAY_BADGE"
        const val TEAM_HOME_BADGE: String = "TEAM_HOME_BADGE"

    }
}