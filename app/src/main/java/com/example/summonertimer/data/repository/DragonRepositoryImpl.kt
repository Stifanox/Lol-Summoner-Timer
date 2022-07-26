package com.example.summonertimer.data.repository

import com.example.summonertimer.data.model.dragon_api.ChampionsInfoModel
import com.example.summonertimer.data.model.dragon_api.SummonerSpellsInfoModel
import com.example.summonertimer.data.remote.DragonApi
import com.example.summonertimer.domain.repository.DragonRepository
import retrofit2.Response
import javax.inject.Inject

class DragonRepositoryImpl @Inject constructor(
    private val dragonApi: DragonApi
):DragonRepository {
    override suspend fun getCurrentVersion(): Response<List<String>> {
        return dragonApi.getCurrentVersion()
    }

    override suspend fun getChampions(version: String): Response<ChampionsInfoModel> {
        return dragonApi.getChampions(version)
    }

    override suspend fun getSummonersSpells(version: String): Response<SummonerSpellsInfoModel> {
        return dragonApi.getSummonersSpells(version)
    }


}