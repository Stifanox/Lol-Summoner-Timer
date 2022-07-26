package com.example.summonertimer.data.model.summoner_api

data class SummonerInfoModel(
    val accountId: String = "",
    val id: String = "",
    val name: String = "",
    val profileIconId: Int = 0,
    val puuid: String = "",
    val revisionDate: Long = 0,
    val summonerLevel: Int = 0
)