package com.ncs.guessr.ui.theme

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ncs.guessr.utils.endAnim
import com.ncs.guessr.utils.zeroScore

@Composable
fun EndScreen(score:Int,navController: NavController){
    var isshareClicked by remember {
        mutableStateOf(false)
    }
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
            Spacer(modifier = Modifier.height(20.dp))
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
        if (isshareClicked){
            isshareClicked=false
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Are you a car enthusiast? Can you beat me in this game? Download here: https://rb.gy/n1y3d ")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            val context = LocalContext.current
            context.startActivity(shareIntent)
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
                zeroScore()
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
