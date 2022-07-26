package com.example.summonertimer.presentation.utils

fun getServerAlias(serverAlias:String):String{
    return when(serverAlias){
        "EUNE" -> "eun1"
        "EUW"-> "euw1"
        "NA" -> "na1"
        "KR" -> "kr"
        "BR" -> "br1"
        "LA1" -> "la1"
        "LA2" -> "la2"
        "RU" -> "ru"
        "JP" -> "jp1"
        "OC" -> "oc1"
        "TR" -> "tr1"
        else -> ""
    }
}