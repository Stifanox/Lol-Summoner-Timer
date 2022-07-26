package com.example.summonertimer.domain.repository

import com.example.summonertimer.data.model.summoner_api.GameInfo.GameInfo
import com.example.summonertimer.data.model.summoner_api.SummonerInfoModel
import retrofit2.Response

interface SummonerRepository {

    suspend fun getSummonerInfo(url:String):Response<SummonerInfoModel>

    suspend fun getGameInfo(url:String):Response<GameInfo>
}