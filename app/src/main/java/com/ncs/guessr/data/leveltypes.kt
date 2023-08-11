package com.ncs.guessr.data

import android.graphics.Color

data class leveltypes(
    val title:String
){
    fun getEmoji(): String {
        when (title) {
            "Newbie" -> return "\uD83D\uDD30"
            "Moderate" -> return "\uD83D\uDE00"
            else -> return "\uD83E\uDD77"
        }
    }
    fun getBgColor(): Int {
        when (title) {
            "Newbie" -> return Color.GREEN
            "Moderate" -> return Color.BLUE
            else -> return Color.RED
        }
    }
//    fun levelClick(): String {
//        when (title) {
//            "Newbie" -> return "Newbie"
//            "Moderate" -> return "Moderate"
//            else -> return "Master"
//        }
//    }

}


