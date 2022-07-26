package com.example.summonertimer.presentation.enemies_summoners_spells.utils

//const val API_KEY = "RGAPI-9f02fdf7-e1d4-46c0-80a6-5a1d1a73d49f"

class Wrapper{
    companion object{
        init {
            System.loadLibrary("keys")
        }

    }
    external fun getKey():String

}


fun provideSummonerNameLink(
    summonerName: String,
    serverAlias: String
):String {

    val key = Wrapper().getKey()
    return "https://$serverAlias.api.riotgames.com/lol/summoner/v4/summoners/by-name/$summonerName?api_key=$key"
}

fun provideGameInfoLink(
    summonerAccountId: String,
    serverAlias: String
):String{
    val key = Wrapper().getKey()
    return "https://$serverAlias.api.riotgames.com/lol/spectator/v4/active-games/by-summoner/$summonerAccountId?api_key=$key"
}