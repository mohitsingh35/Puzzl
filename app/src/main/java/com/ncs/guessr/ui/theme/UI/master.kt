package com.ncs.guessr.ui.theme.UI

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ncs.guessr.LevelData
import com.ncs.guessr.R
import com.ncs.guessr.common.loadingscreen

@Composable
fun masterLevel(viewModel: MasterViewModel = hiltViewModel(),navController: NavController){
//    val data = mutableListOf(
//        LevelData(mutableListOf("BMW", "FORD", "MERCEDES", "BUGGATI"), "BUGGATI", R.drawable.bulb),
//        LevelData(mutableListOf("SUZUKI", "MARUTI", "KOINEGESGG", "BUGGATI"), "SUZUKI", R.drawable.button),
//        LevelData(mutableListOf("BMW", "FORD", "MERCEDES", "BUGGATI"), "BUGGATI", R.drawable.bulb)
//    )
//    LevelHosting(data = data)
    val res = viewModel.res.value
    val serverData = res.item

    if (serverData == null || serverData.isEmpty()) {
        loadingscreen()
    } else {
        LevelHosting(data = serverData, navController = navController )
    }

}