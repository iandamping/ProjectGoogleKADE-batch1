package com.example.junemon.kotlinnetworking.network

import com.example.junemon.kotlinnetworking.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(ApiConfig.getAllTeams)
    fun getAllTeamsData(@Query("l") teamName: String?): Observable<TeamModel>

    @GET(ApiConfig.getPastEvent)
    fun getPastEventData(@Query("id") teamName: String?): Observable<MainModelLastMatch>

    @GET(ApiConfig.getAllLeague)
    fun getAllLeague(): Observable<AllLeagueModel>

    @GET(ApiConfig.getTeamInfo)
    fun getLastHomeTeamInfo(@Query("id") teamName: String?): Observable<DetailLastMatchModel>

    @GET(ApiConfig.getTeamInfo)
    fun getLastAwayTeamInfo(@Query("id") teamName: String?): Observable<DetailLastMatchModel>

    @GET(ApiConfig.getNextEvent)
    fun getNextEventData(@Query("id") teamName: String?): Observable<MainModelNextMatch>

    @GET(ApiConfig.getTeamInfo)
    fun getNextHomeTeamInfo(@Query("id") teamName: String?): Observable<DetailNextMatchModel>

    @GET(ApiConfig.getTeamInfo)
    fun getNextAwayTeamInfo(@Query("id") teamName: String?): Observable<DetailNextMatchModel>

    @GET(ApiConfig.getAllPlayers)
    fun getAllPlayer(@Query("id") allPlayer: String?): Observable<AllPlayer>

    @GET(ApiConfig.getTeamDetails)
    fun getTeamDetail(@Query("id") teamName: String?): Observable<TeamDetailModel>

    @GET(ApiConfig.getTeamSearchDetails)
    fun getTeamSearchDetails(@Query("t") teamName: String?): Observable<TeamModel>

    @GET(ApiConfig.getSearchNextEvent)
    fun getSearchNextEventData(@Query("id") teamName: String?): Observable<MainModelNextMatch>

    @GET(ApiConfig.getSearchLastEvent)
    fun getSearchPastEventData(@Query("id") teamName: String?): Observable<MainModelLastMatch>
}