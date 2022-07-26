package com.example.summonertimer.presentation.enemies_summoners_spells.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.delay

@Composable
fun SummonerSpellTimerComponent(
    timer: Double,
    imageName: String,

) {
    val totalTimer = timer
    var currentTime by remember { mutableStateOf(timer) }
    var isRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = currentTime, key2 = isRunning) {
        if (currentTime > 0 && isRunning) {
            delay(1000L)
            currentTime -= 1
        } else if (currentTime < 0) {
            currentTime = totalTimer
            isRunning = !isRunning
        }
    }


    val minutes = currentTime.toInt() / 60
    val seconds = currentTime.toInt() % 60
    val timeString = "$minutes:${if (seconds >= 10) seconds else "0$seconds"}"

    Box(modifier = Modifier) {
        AsyncImage(
            model = "http://ddragon.leagueoflegends.com/cdn/12.13.1/img/spell/$imageName",
            contentDescription = null,
            colorFilter = if (isRunning) ColorFilter.tint(
                color = Color(0xFF8B8B8B),
                blendMode = BlendMode.Multiply
            ) else null,
            modifier = Modifier
                .size(70.dp)
                .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                    isRunning = !isRunning
                    currentTime = totalTimer
                }
        )
        if (isRunning) {
            Text(
                text = timeString,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 26.sp,
            )
        }
    }

}