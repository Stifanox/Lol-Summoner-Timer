package com.example.summonertimer.presentation.enemies_summoners_spells.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summonertimer.R
import com.example.summonertimer.data.model.dragon_api.ChampionsInfoModel
import com.example.summonertimer.data.model.dragon_api.SummonerSpellsInfoModel
import com.example.summonertimer.data.remote.DragonApi
import com.example.summonertimer.domain.logic.CalculateSummonerSpellTimer
import com.example.summonertimer.domain.logic.RetrieveEnemySpells
import com.example.summonertimer.domain.model.EnemyInfo
import com.example.summonertimer.domain.model.PlayerData
import com.example.summonertimer.domain.repository.SummonerRepository
import com.example.summonertimer.presentation.enemies_summoners_spells.utils.provideGameInfoLink
import com.example.summonertimer.presentation.enemies_summoners_spells.utils.provideSummonerNameLink
import com.example.summonertimer.presentation.utils.getServerAlias
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class GameInfoViewModel @Inject constructor(
    private val summonerRepository: SummonerRepository,
    private val dragonApi: DragonApi
) : ViewModel() {

    private var enemyListInfo: MutableList<EnemyInfo> by mutableStateOf(mutableListOf())
    private var championsInfo: ChampionsInfoModel by mutableStateOf(ChampionsInfoModel())
    private var spellsInfo: SummonerSpellsInfoModel by mutableStateOf(SummonerSpellsInfoModel())
    var listDataForComponents: List<PlayerData> by mutableStateOf(listOf())
        private set
    var exception by mutableStateOf(false)
        private set
    var exceptionMessage by mutableStateOf(0)
        private set

    fun getSummonerInfoFromName(summonerName: String, serverAlias: String) {
        resetValues()
        viewModelScope.launch {
            val response = try {
                summonerRepository.getSummonerInfo(
                    provideSummonerNameLink(
                        summonerName,
                        getServerAlias(serverAlias)
                    )
                )
            } catch (e: IOException) {
                exception = true
                exceptionMessage = R.string.exception_io_error
                return@launch
            } catch (e: HttpException) {
                exception = true
                exceptionMessage = R.string.exception_http_error
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                getGameInfo(response.body()!!.id, serverAlias)
            } else {
                exception = true
                exceptionMessage = R.string.summoner_not_found
            }
        }
    }

    private fun getGameInfo(summonerId: String, serverAlias: String) {
        viewModelScope.launch {
            val response = try {
                summonerRepository.getGameInfo(
                    provideGameInfoLink(
                        summonerId,
                        getServerAlias(serverAlias)
                    )
                )
            } catch (e: IOException) {
                exception = true
                exceptionMessage = R.string.exception_io_error
                return@launch
            } catch (e: HttpException) {
                exception = true
                exceptionMessage = R.string.exception_http_error
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                RetrieveEnemySpells.init(summonerId = summonerId, gameInfo = response.body()!!)
                enemyListInfo = RetrieveEnemySpells.listOfPlayers
                getVersion()
            } else {
                exception = true
                exceptionMessage = R.string.game_not_found
            }
        }
    }

    private fun getVersion() {
        viewModelScope.launch {
            val version = try {
                dragonApi.getCurrentVersion()
            } catch (e: IOException) {
                return@launch
            } catch (e: HttpException) {
                return@launch
            }
            if (version.isSuccessful && version.body() != null) {
                getChampionsInfoAndSummonerSpellsInfo(version.body()!![0])
            }
        }
    }

    private fun getChampionsInfoAndSummonerSpellsInfo(version: String) {
        viewModelScope.launch {
            val responseChampions = try {
                dragonApi.getChampions(version)
            } catch (e: IOException) {
                return@launch
            } catch (e: HttpException) {
                return@launch
            }
            if (responseChampions.isSuccessful && responseChampions.body() != null) {
                championsInfo = responseChampions.body()!!
            }

            val responseSpells = try {
                dragonApi.getSummonersSpells(version)
            } catch (e: IOException) {
                return@launch
            } catch (e: HttpException) {
                return@launch
            }
            if (responseSpells.isSuccessful && responseSpells.body() != null) {
                spellsInfo = responseSpells.body()!!
            }
            createComponentData()
        }
    }

    private fun createComponentData() {
        CalculateSummonerSpellTimer.init(enemyListInfo, championsInfo, spellsInfo)
        listDataForComponents = CalculateSummonerSpellTimer.listDataForComponents
    }

    fun resetException() {
        exception = false
    }

    private fun resetValues() {
        enemyListInfo.clear()
        championsInfo = ChampionsInfoModel()
        spellsInfo = SummonerSpellsInfoModel()
    }

}