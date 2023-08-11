package com.ncs.guessr.ui.theme.UI

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ncs.guessr.LevelData
import com.ncs.guessr.common.CommonDialog
import com.ncs.guessr.common.loadingscreen
import com.ncs.guessr.firebase.RealTimeModelResponse
import kotlinx.coroutines.CoroutineScope

@Composable
fun newbieLevel(viewModel: NewbieViewModel = hiltViewModel(),navController: NavController) {
    val res = viewModel.res.value
    val serverData = res.item
    Log.d("mohitTest",serverData.toString())
    if (serverData == null || serverData.isEmpty()) {
        loadingscreen()
    } else {
        LevelHosting(data = serverData,navController)
    }
}

