package com.example.summonertimer.presentation.summoner_name.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.summonertimer.R
import com.example.summonertimer.domain.routes.Routes

@Composable
fun SummonerNameScreen(
    navController: NavController
) {
    val context = LocalContext.current
    var summonerName by remember { mutableStateOf("") }
    val serverList by remember {
        mutableStateOf(
            listOf(
                "EUNE",
                "EUW",
                "NA",
                "KR",
                "BR",
                "LA1",
                "LA2",
                "RU",
                "JP",
                "OC",
                "TR"
            )
        )
    }
    var selectedServer by remember {
        mutableStateOf(serverList[0])
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.surface) {
            Text(
                text = stringResource(R.string.type_summoner),
                fontSize = 16.sp
            )
            TextField(value = summonerName, onValueChange = { summonerName = it })
            Text(
                text = stringResource(id = R.string.choose_server),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            ServerList(
                serverList = serverList,
                changeServer = { selectedServer = it },
            )
            Button(onClick = {
                if (summonerName.isNotEmpty()) {
                    navController.navigate("show_summonerInfo/$summonerName/$selectedServer") {
                        launchSingleTop = true
                    }
                } else {
                    Toast.makeText(context, R.string.warning_message_empty, Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(text = stringResource(R.string.search))
            }
        }
    }

}