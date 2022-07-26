package com.example.summonertimer.data.repository


import com.example.summonertimer.data.model.summoner_api.GameInfo.GameInfo
import com.example.summonertimer.data.model.summoner_api.SummonerInfoModel
import com.example.summonertimer.data.remote.SummonerApi
import com.example.summonertimer.domain.repository.SummonerRepository
import retrofit2.Response
import javax.inject.Inject

class SummonerRepositoryImpl @Inject constructor (
    private val summonerApi: SummonerApi
):SummonerRepository {
    override suspend fun getSummonerInfo(url: String):Response<SummonerInfoModel> {
        return summonerApi.getSummonerInfo(url)
    }

    override suspend fun getGameInfo(url: String): Response<GameInfo> {
        return summonerApi.getGameInfo(url)
    }
}