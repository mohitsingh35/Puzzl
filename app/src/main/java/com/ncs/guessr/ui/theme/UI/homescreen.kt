package com.ncs.guessr.ui.theme.UI

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    var isshareClicked by remember {
        mutableStateOf(false)
    }
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
                    Spacer(modifier = Modifier.height(150.dp))
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        Box (
                            Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .width(120.dp)
                                .clickable {
                                    isshareClicked = true
                                }
                                .background(Color.White)
                                .height(45.dp), contentAlignment = Alignment.Center
                        ){
                            Row {
                                Image(Icons.Filled.Share, contentDescription = "share" )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "Share", color = Color.Black, fontWeight = FontWeight.ExtraBold)

                            }
                        }

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
        if (isshareClicked){
            isshareClicked=false
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Are you a car enthusiast? Can you beat me in this game? Download here: https://rb.gy/n1y3d ")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            val context = LocalContext.current
            context.startActivity(shareIntent)    }
    }
}
