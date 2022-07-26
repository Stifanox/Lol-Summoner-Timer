package com.example.summonertimer.domain.model

import com.example.summonertimer.data.model.summoner_api.GameInfo.Perks

data class EnemyInfo(
    val championId:Int,
    val perks: Perks,
    val listOfSpells:List<Int>
)
