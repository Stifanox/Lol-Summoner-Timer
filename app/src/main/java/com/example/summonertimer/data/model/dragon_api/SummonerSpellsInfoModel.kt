package com.example.summonertimer.data.model.dragon_api

import com.example.summonertimer.data.model.dragon_api.summoner_spells_info.SummonerSpellsInfo

data class SummonerSpellsInfoModel(
    val type:String = "",
    val version:String = "",
    val data:HashMap<String,SummonerSpellsInfo> = HashMap()
)
