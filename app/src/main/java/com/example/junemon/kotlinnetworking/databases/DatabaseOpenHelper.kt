package com.example.junemon.kotlinnetworking.databases

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.junemon.kotlinnetworking.R
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, Integer.toString(R.string.database_name), null, 1) {
    companion object {
        private var instance: DatabaseOpenHelper? = null

        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(ctx)
            }
            return instance!!
        }
    }

    override fun onCreate(p0: SQLiteDatabase) {
        p0.createTable(DatabaseModel.TABLE_FAVORITE, true,
                DatabaseModel.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DatabaseModel.AWAY_DEFENSE to TEXT,
                DatabaseModel.AWAY_FORWARD to TEXT,
                DatabaseModel.AWAY_GOALKEPEER to TEXT,
                DatabaseModel.AWAY_GOAL_DETAILS to TEXT,
                DatabaseModel.AWAY_MIDFIELD to TEXT,
                DatabaseModel.DATE_EVENT to TEXT,
                DatabaseModel.HOME_DEFENSE to TEXT,
                DatabaseModel.HOME_FORWARD to TEXT,
                DatabaseModel.HOME_GOALKEPEER to TEXT,
                DatabaseModel.HOME_GOAL_DETAILS to TEXT,
                DatabaseModel.HOME_MIDFIELD to TEXT,
                DatabaseModel.TEAM_HOME_ID to TEXT + UNIQUE,
                DatabaseModel.TEAM_HOME_NAME to TEXT,
                DatabaseModel.TEAM_HOME_SCORE to TEXT,
                DatabaseModel.TEAM_AWAY_ID to TEXT + UNIQUE,
                DatabaseModel.TEAM_AWAY_NAME to TEXT,
                DatabaseModel.TEAM_AWAY_SCORE to TEXT,
                DatabaseModel.TEAM_AWAY_BADGE to TEXT,
                DatabaseModel.TEAM_HOME_BADGE to TEXT)

        p0.createTable(DatabaseLeagueModel.TABLE_LEAGUE, true,
                DatabaseLeagueModel.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DatabaseLeagueModel.ID_LEAGUE to TEXT,
                DatabaseLeagueModel.STR_LEAGUE to TEXT,
                DatabaseLeagueModel.STR_SPORT to TEXT,
                DatabaseLeagueModel.STR_LEAGUE_ALTERNATE to TEXT)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.dropTable(Integer.toString(R.string.table_name), true)
        p0.dropTable(Integer.toString(R.string.table_league_name), true)
    }
}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)