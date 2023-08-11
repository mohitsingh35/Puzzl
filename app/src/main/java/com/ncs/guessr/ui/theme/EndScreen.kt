package com.ncs.guessr.ui.theme

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ncs.guessr.ui.theme.UI.endAnim

@Composable
fun EndScreen(score:Int,navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        Column {
            Box(modifier = Modifier
                .fillMaxWidth()
                , contentAlignment = Alignment.Center) {
                endAnim()
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = "Congratulations!", color = Color.White, fontSize = 35.sp, fontWeight = FontWeight.ExtraBold)
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp), contentAlignment = Alignment.Center) {

                    Text(text = "Your Score is:", color = Color.White, fontSize = 20.sp, textAlign = TextAlign.Center,fontWeight = FontWeight.Medium)

            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp), contentAlignment = Alignment.Center) {
                Box(modifier = Modifier
                    .height(50.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White), contentAlignment = Alignment.Center) {
                    Text(
                        text = score.toString(),
                        color = Color.Black,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
            Spacer(modifier = Modifier.height(180.dp))
            Box (modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Box(modifier = Modifier
                    .height(40.dp)
                    .width(350.dp)
                    .background(Color.LightGray)
                    .clickable { navController.navigate("homescreen") }
                    .clip(
                        RoundedCornerShape(10.dp)
                    ), contentAlignment = Alignment.Center){
                    Text(text = "Return", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
@Composable
fun zeroScoreScreen(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        Column {
            Box(modifier = Modifier
                .size(300.dp)
                .padding(start = 75.dp), contentAlignment = Alignment.Center) {
                com.ncs.guessr.ui.theme.UI.zeroScore()
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = "Your Score was 0", color = Color.White, fontSize = 35.sp,fontWeight = FontWeight.ExtraBold)
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp), contentAlignment = Alignment.Center) {
                Text(text = "Please Try Again", color = Color.White, fontSize = 20.sp, textAlign = TextAlign.Center,fontWeight = FontWeight.ExtraBold)
            }
            Spacer(modifier = Modifier.height(180.dp))
            Box (modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Box(modifier = Modifier
                    .height(40.dp)
                    .width(350.dp)
                    .background(Color.LightGray)
                    .clickable { navController.navigate("homescreen") }
                    .clip(
                        RoundedCornerShape(10.dp)
                    ), contentAlignment = Alignment.Center){
                    Text(text = "Return", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}