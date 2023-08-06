package com.ncs.guessr.ui.theme.UI

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ncs.guessr.leveltypes

@Composable
fun levels(leveltypes: leveltypes,navController: NavController){

    Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 10.dp, bottom = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                navController.navigate(leveltypes.title)
            }
            .background(
                Color(leveltypes.getBgColor())
            ), contentAlignment = Alignment.Center){
            Row {
                Text(text = leveltypes.title, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = leveltypes.getEmoji(),fontSize = 15.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}