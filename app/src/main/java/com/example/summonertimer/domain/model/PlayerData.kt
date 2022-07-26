package com.example.summonertimer.domain.model


data class PlayerData(
    val imageChampionName: String,
    val imageSpellOneName: String,
    val imageSpellTwoName: String,
    val spellTimerOneBase: Int,
    val spellTimerTwoBase: Int,
    var spellTimerOneCalculated: Double,
    var spellTimerTwoCalculated: Double,
    val doesHaveRune: Boolean,
)
