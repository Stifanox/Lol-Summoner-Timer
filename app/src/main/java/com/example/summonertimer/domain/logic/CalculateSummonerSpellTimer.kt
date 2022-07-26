package com.example.summonertimer.domain.logic

import com.example.summonertimer.data.model.dragon_api.ChampionsInfoModel
import com.example.summonertimer.data.model.dragon_api.SummonerSpellsInfoModel
import com.example.summonertimer.data.model.dragon_api.summoner_spells_info.SummonerSpellsInfo
import com.example.summonertimer.domain.model.EnemyInfo
import com.example.summonertimer.domain.model.PlayerData

object CalculateSummonerSpellTimer {

    val listDataForComponents:MutableList<PlayerData> = mutableListOf()

    fun init(
        listOfPlayers: List<EnemyInfo>,
        championInfo: ChampionsInfoModel,
        spellsInfoModel: SummonerSpellsInfoModel
    ) {
        listDataForComponents.clear()
        listOfPlayers.forEach { player ->
            //HARDCODE NUMBER MIGHT CHANGE IN FUTURE
            val doesHaveRune = player.perks.perkIds.contains(8347)
            val imageChampionName = championInfo.data.values.find { championInfo ->
                championInfo.key == player.championId
            }?.image?.full
            val spellOne = spellsInfoModel.data.values.find { summonerSpellInfo ->
                summonerSpellInfo.key.toInt() == player.listOfSpells[0]
            }
            val spellTwo = spellsInfoModel.data.values.find { summonerSpellInfo ->
                summonerSpellInfo.key.toInt() == player.listOfSpells[1]
            }

            val imageSpellOneName = spellOne?.image?.full
            val imageSpellTwoName = spellTwo?.image?.full
            val baseSpellTimerOne = (spellOne as SummonerSpellsInfo).cooldownBurn.toInt()
            val baseSpellTimerTwo = (spellTwo as SummonerSpellsInfo).cooldownBurn.toInt()
            val spellTimerOne = calculateInitialTimer(baseSpellTimerOne, doesHaveRune)
            val spellTimerTwo = calculateInitialTimer(baseSpellTimerTwo,doesHaveRune)

            listDataForComponents.add(PlayerData(
                imageChampionName!!,
                imageSpellOneName!!,
                imageSpellTwoName!!,
                baseSpellTimerOne,
                baseSpellTimerTwo,
                spellTimerOne,
                spellTimerTwo,
                doesHaveRune
            ))
        }
    }

    private fun calculateInitialTimer(summonerTime: Int, doesHaveRune: Boolean): Double {
        return if (!doesHaveRune) summonerTime.toDouble()
        else {
            //Number of percent expressed as double
            val percentInt = 100.0 - (10000.0/(100.0+18.0))
            //Convert int to percent (e.g. 15.0 -> 0.15)
            val percent = percentInt/100

            summonerTime - summonerTime * percent
        }
    }
}