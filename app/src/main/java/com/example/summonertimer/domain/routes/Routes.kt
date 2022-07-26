package com.example.summonertimer.domain.routes

import androidx.compose.runtime.Composable
import com.example.summonertimer.presentation.summoner_name.ui.SummonerNameScreen

sealed class Routes(val routeName:String) {
    object StartScreen:Routes("start_screen")
    object ShowSummonerInfo:Routes("show_summonerInfo/{summonerName}")
}

