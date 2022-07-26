package com.example.summonertimer.presentation.enemies_summoners_spells.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.summonertimer.domain.model.PlayerData

@Composable
fun SummonerSpellImageRowComponent(
    playerData: PlayerData
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        val championNameImage = playerData.imageChampionName
        val summonerNameOneImage = playerData.imageSpellOneName
        val summonerNameTwoImage = playerData.imageSpellTwoName
        var summonerSpellTimerOne by rememberSaveable { mutableStateOf(playerData.spellTimerOneCalculated) }
        var summonerSpellTimerTwo by rememberSaveable { mutableStateOf(playerData.spellTimerTwoCalculated) }
        var isBootsSelected by rememberSaveable { mutableStateOf(false) }

        AsyncImage(
            model = "http://ddragon.leagueoflegends.com/cdn/12.13.1/img/champion/$championNameImage",
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        SummonerSpellTimerComponent(
            timer = summonerSpellTimerOne,
            imageName = summonerNameOneImage,
        )
        SummonerSpellTimerComponent(
            timer = summonerSpellTimerTwo,
            imageName = summonerNameTwoImage,
        )
        //hardcoded item
        Box {
            AsyncImage(
                model = "http://ddragon.leagueoflegends.com/cdn/12.13.1/img/item/3158.png",
                colorFilter = if (isBootsSelected) ColorFilter.tint(
                    color = Color(0xFF8B8B8B),
                    blendMode = BlendMode.Multiply
                ) else null,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clickable(indication = null, interactionSource = MutableInteractionSource()) {
                        isBootsSelected = !isBootsSelected
                        summonerSpellTimerOne = recalculateTimers(
                            isBootsSelected,
                            playerData.doesHaveRune,
                            playerData.spellTimerOneBase
                        )
                        summonerSpellTimerTwo = recalculateTimers(
                            isBootsSelected,
                            playerData.doesHaveRune,
                            playerData.spellTimerTwoBase
                        )
                    }
            )
            if (isBootsSelected) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(40.dp),
                    tint = Color.White
                )
            }
        }

    }
}

fun recalculateTimers(doesHaveBoots: Boolean, doesHaveRune: Boolean, baseTimer: Int): Double {
    var sumOHaste = 0
    if (doesHaveBoots) sumOHaste += 12
    if (doesHaveRune) sumOHaste += 18

    return calculateTimer(baseTimer, sumOHaste)
}

fun calculateTimer(baseTimer: Int, haste: Int): Double {
    val percentInt = 100.0 - (10000.0 / (100.0 + haste))
    val percent = percentInt / 100
    return baseTimer - baseTimer * percent
}

