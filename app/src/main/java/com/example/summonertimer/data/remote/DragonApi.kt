package com.example.summonertimer.data.remote

import com.example.summonertimer.data.model.dragon_api.ChampionsInfoModel
import com.example.summonertimer.data.model.dragon_api.SummonerSpellsInfoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DragonApi {

    @GET("api/versions.json")
    suspend fun getCurrentVersion():Response<List<String>>
    //pobrać championy

    @GET("cdn/{version}/data/en_US/champion.json")
    suspend fun getChampions(@Path("version") version:String):Response<ChampionsInfoModel>

    @GET("cdn/{version}/data/en_US/summoner.json")
    suspend fun getSummonersSpells(@Path("version") version: String):Response<SummonerSpellsInfoModel>
}