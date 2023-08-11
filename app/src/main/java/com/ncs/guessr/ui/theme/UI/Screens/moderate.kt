package com.ncs.guessr.ui.theme.UI.Screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ncs.guessr.common.loadingscreen
import com.ncs.guessr.viewmodels.ModerateViewModel

@Composable
fun moderateLevel(viewModel: ModerateViewModel = hiltViewModel(), navController: NavController) {
//    val data = mutableListOf(
//        LevelData(mutableListOf("MERCEDES", "PORSCHE", "JAGUAR", "HONDA"), "JAGUAR", R.drawable.bulb),
//        LevelData(mutableListOf("SUZUKI", "MARUTI", "KOINEGESGG", "BUGGATI"), "SUZUKI", R.drawable.button),
//        LevelData(mutableListOf("MERCEDES", "PORSCHE", "JAGUAR", "HONDA"), "JAGUAR", R.drawable.bulb),
//    )
//    LevelHosting(data = data)
    val res = viewModel.res.value
    val serverData = res.item

    if (serverData == null || serverData.isEmpty()) {
        loadingscreen()
    } else {
        LevelHosting(data = serverData,navController)
    }
}




