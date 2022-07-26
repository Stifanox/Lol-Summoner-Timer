package com.example.summonertimer.presentation.enemies_summoners_spells.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.summonertimer.di.AppModule
import com.example.summonertimer.presentation.enemies_summoners_spells.viewmodel.GameInfoViewModel
import com.example.summonertimer.presentation.utils.getServerAlias

@Composable
fun GetEnemiesSummonerSpellScreen(
    summonerName: String,
    server:String,
    returnToHomeScreen: () -> Unit,
    gameInfoViewModel: GameInfoViewModel = hiltViewModel()
) {

    val dataForComponents = gameInfoViewModel.listDataForComponents.toMutableList()
    gameInfoViewModel.getSummonerInfoFromName(summonerName,server)
    val exception = gameInfoViewModel.exception
    val exceptionMessage = gameInfoViewModel.exceptionMessage

    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.surface) {
        //Jeżeli jest błąd to wyświetl ekran błędu
        if (exception) {
            GameNotFoundScreen(exceptionMessage) {
                gameInfoViewModel.resetException()
                returnToHomeScreen()
            }
            return@CompositionLocalProvider
        }

        if (dataForComponents.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                for (playerData in dataForComponents) {
                    SummonerSpellImageRowComponent(
                        playerData
                    )
                }
            }
        }
    }


}