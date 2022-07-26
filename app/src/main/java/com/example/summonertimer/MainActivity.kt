package com.example.summonertimer

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.summonertimer.presentation.summoner_name.ui.SummonerNameScreen
import com.example.summonertimer.presentation.enemies_summoners_spells.ui.GetEnemiesSummonerSpellScreen
import com.example.summonertimer.ui.theme.SummonerTimerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setContent {
            SummonerTimerTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "start_screen",
        modifier = Modifier.background(
            Color(0xff3b3b3b)
        )
    ) {
        composable("start_screen") { SummonerNameScreen(navController) }
        composable(
            "show_summonerInfo/{summonerName}/{server}",
            arguments = listOf(
                navArgument("summonerName") { type = NavType.StringType },
                navArgument("server") { type = NavType.StringType })
        ) { backStackEntry ->
            GetEnemiesSummonerSpellScreen(
                backStackEntry.arguments?.getString("summonerName") as String,
                backStackEntry.arguments?.getString("server") as String,
                {navController.navigateUp()}
            )
        }
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}