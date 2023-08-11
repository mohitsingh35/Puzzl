package com.ncs.guessr.data

import androidx.annotation.DrawableRes

data class LevelData(
    val options:MutableList<String>,
    val answer:String,
    @DrawableRes val logo:Int,

){
}

