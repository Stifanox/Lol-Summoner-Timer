package com.example.summonertimer.domain.logic

import com.example.summonertimer.data.model.summoner_api.GameInfo.GameInfo
import com.example.summonertimer.domain.model.EnemyInfo

object RetrieveEnemySpells {
    private var teamId:Int = 0
    private lateinit var gameInfo: GameInfo
    private lateinit var summonerId: String

    var listOfPlayers:MutableList<EnemyInfo> = mutableListOf()
        private set

    fun init(summonerId:String,gameInfo: GameInfo){
        this.summonerId = summonerId
        this.gameInfo = gameInfo

        val playerInfo = gameInfo.participants.filter{
            it.summonerId == this.summonerId
        }
        teamId = playerInfo[0].teamId
        getEnemySpells()
    }

    private fun getEnemySpells(){

        val enemyList = gameInfo.participants.filter { player ->
            player.teamId != teamId
        }
        enemyList.forEach { player ->
            if (listOfPlayers.size >=5) return
            listOfPlayers.add(EnemyInfo(player.championId,player.perks, listOf(player.spell1Id,player.spell2Id)))
        }

    }
}