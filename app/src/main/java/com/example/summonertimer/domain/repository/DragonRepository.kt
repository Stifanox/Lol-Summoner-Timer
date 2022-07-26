package com.example.summonertimer.domain.repository

import com.example.summonertimer.data.model.dragon_api.ChampionsInfoModel
import com.example.summonertimer.data.model.dragon_api.SummonerSpellsInfoModel
import retrofit2.Response

interface DragonRepository {

    suspend fun getCurrentVersion():Response<List<String>>

    suspend fun getChampions(version:String):Response<ChampionsInfoModel>

    suspend fun getSummonersSpells(version: String):Response<SummonerSpellsInfoModel>
}