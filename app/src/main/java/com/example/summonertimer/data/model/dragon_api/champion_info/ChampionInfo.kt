package com.example.summonertimer.data.model.dragon_api.champion_info

import com.example.summonertimer.data.model.dragon_api.ImageModel

data class ChampionInfo(
    val blurb: String,
    val id: String,
    val image: ImageModel,
    val info: Info,
    val key: Int,
    val name: String,
    val partype: String,
    val stats: Stats,
    val tags: List<String>,
    val title: String,
    val version: String
)