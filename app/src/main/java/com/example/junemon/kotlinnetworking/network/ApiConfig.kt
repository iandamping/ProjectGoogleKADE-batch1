package com.example.junemon.kotlinnetworking.network

class ApiConfig {
    companion object {
        val baseUrl: String = "https://www.thesportsdb.com/"
        const val getPastEvent: String = "api/v1/json/1/eventspastleague.php"
        const val getTeamInfo: String = "api/v1/json/1/lookupteam.php"
        const val getNextEvent: String = "api/v1/json/1/eventsnextleague.php"
        const val getAllLeague: String = "api/v1/json/1/all_leagues.php"
        const val getAllTeams:String = "api/v1/json/1/search_all_teams.php"
    }
}