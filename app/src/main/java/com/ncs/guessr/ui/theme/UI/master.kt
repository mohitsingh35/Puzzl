package com.ncs.guessr.ui.theme.UI

import androidx.compose.runtime.Composable
import com.ncs.guessr.LevelData
import com.ncs.guessr.R

@Composable
fun masterLevel(){
    val data = mutableListOf(
        LevelData(mutableListOf("BMW", "FORD", "MERCEDES", "BUGGATI"), "BUGGATI", R.drawable.bulb),
        LevelData(mutableListOf("SUZUKI", "MARUTI", "KOINEGESGG", "BUGGATI"), "SUZUKI", R.drawable.button),
        LevelData(mutableListOf("BMW", "FORD", "MERCEDES", "BUGGATI"), "BUGGATI", R.drawable.bulb)
    )
    LevelHosting(data = data)

}