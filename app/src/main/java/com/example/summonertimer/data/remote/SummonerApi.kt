package com.example.summonertimer.data.remote

import com.example.summonertimer.data.model.summoner_api.GameInfo.GameInfo
import com.example.summonertimer.data.model.summoner_api.SummonerInfoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface SummonerApi{

    @GET
    suspend fun getSummonerInfo(@Url url:String):Response<SummonerInfoModel>

    @GET
    suspend fun getGameInfo(@Url url:String):Response<GameInfo>
}