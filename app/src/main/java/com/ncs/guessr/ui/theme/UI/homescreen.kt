package com.ncs.guessr.ui.theme.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ncs.guessr.R
import com.ncs.guessr.leveltypes

@Composable
fun homeScreen(navController: NavController) {
    val context= LocalContext.current
    var isSettingDialogVisible by remember { mutableStateOf(false) }
    var ishelpDialogVisible by remember { mutableStateOf(false) }
    var updatedhelpCount by remember { mutableStateOf(getTextValue(context)) }

    val list = mutableListOf(leveltypes("Newbie"), leveltypes("Moderate"), leveltypes("Master"))

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            ActionBar(onSettingsClicked = { isSettingDialogVisible = true }, onhelpClicked = {ishelpDialogVisible=true},count = updatedhelpCount)

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Column {
                    Spacer(modifier = Modifier.height(40.dp))
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "What's your current level?\nChoose wisely!",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    for (i in 0..2) {
                        levels(leveltypes = list[i], navController)
                    }
                }
            }
        }

        if (isSettingDialogVisible) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                SettingsDialog(
                    isSettingClicked = isSettingDialogVisible,
                    onDismiss = { isSettingDialogVisible = false },
                    context = LocalContext.current
                )
            }
        }
        if (ishelpDialogVisible) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                helpDialog(
                    ishelpclicked = ishelpDialogVisible,
                    onDismiss = { ishelpDialogVisible = false },
                    context = LocalContext.current
                )
            }
        }
    }
}
