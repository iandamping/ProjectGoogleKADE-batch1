package com.example.junemon.kotlinnetworking.network

import com.example.junemon.kotlinnetworking.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(ApiConfig.getPastEvent)
    fun getPastEventData(@Query("id") teamName: String?): Observable<MainModelLastMatch>

    @GET(ApiConfig.getAllLeague)
    fun getAllLeague(): Observable<AllLeagueModel>

    @GET(ApiConfig.getTeamInfo)
    fun getLastHomeTeamInfo(@Query("id") teamName: String?): Observable<DetailLastMatchModel>

    @GET(ApiConfig.getTeamInfo)
    fun getLastAwayTeamInfo(@Query("id") teamName: String?): Observable<DetailLastMatchModel>

    @GET(ApiConfig.getNextEvent)
    fun getNextEventData(): Observable<MainModelNextMatch>

    @GET(ApiConfig.getTeamInfo)
    fun getNextHomeTeamInfo(@Query("id") teamName: String?): Observable<DetailNextMatchModel>

    @GET(ApiConfig.getTeamInfo)
    fun getNextAwayTeamInfo(@Query("id") teamName: String?): Observable<DetailNextMatchModel>
}