package com.example.summonertimer.data.model.summoner_api.GameInfo

data class GameInfo(
    val bannedChampions: List<BannedChampion>,
    val gameId: Long,
    val gameLength: Int,
    val gameMode: String,
    val gameQueueConfigId: Int,
    val gameStartTime: Long,
    val gameType: String,
    val mapId: Int,
    val observers: Observers,
    val participants: List<Participant>,
    val platformId: String
)