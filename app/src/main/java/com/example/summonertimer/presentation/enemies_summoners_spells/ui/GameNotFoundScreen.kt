package com.example.summonertimer.presentation.enemies_summoners_spells.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.summonertimer.R

@Composable
fun GameNotFoundScreen(@StringRes exceptionMessage: Int, returnToHomeScreen: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(exceptionMessage))
        Button(onClick = { returnToHomeScreen() }
        ) {
            Text(text = stringResource(R.string.search_again))
        }
    }
}