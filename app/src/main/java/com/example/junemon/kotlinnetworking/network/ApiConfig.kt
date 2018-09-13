package com.example.junemon.kotlinnetworking.network

class ApiConfig {
    companion object {
        val baseUrl: String = "https://www.thesportsdb.com/"
        const val getPastEvent: String = "api/v1/json/1/eventspastleague.php?id=4328"
        const val getTeamInfo: String = "api/v1/json/1/lookupteam.php"
        const val getNextEvent: String = "api/v1/json/1/eventsnextleague.php?id=4328"
        const val getAllLeague: String = "api/v1/json/1/all_leagues.php"
    }
}