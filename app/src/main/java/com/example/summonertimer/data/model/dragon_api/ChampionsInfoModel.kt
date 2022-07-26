package com.example.summonertimer.data.model.dragon_api

import com.example.summonertimer.data.model.dragon_api.champion_info.ChampionInfo

data class ChampionsInfoModel(
    val type: String = "",
    val version: String = "",
    val data: HashMap<String, ChampionInfo> = HashMap()
)
