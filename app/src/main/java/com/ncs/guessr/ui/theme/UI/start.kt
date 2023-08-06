package com.ncs.guessr.ui.theme.UI


import android.media.MediaPlayer
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ncs.guessr.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

@Composable
fun start(navController: NavController){
    var isClicked by remember {
        mutableStateOf(false)
    }
    Column (
        Modifier
            .fillMaxSize()
            .background(Color.Black)){
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 150.dp), contentAlignment = Alignment.Center){
            Column {
                Pulsating {
                    Text(text = "Are you a real Car\n     Enthusiast?", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
                }
                Spacer(modifier =Modifier.height(80.dp))
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp), contentAlignment = Alignment.Center){
            Image(painter = painterResource(id = R.drawable.button), contentDescription = "start", modifier = Modifier
                .clickable {
                    isClicked = true
                    navController.navigate("homescreen")
                }
                .size(150.dp))
        }

    }
}

@Composable
fun Pulsating(pulseFraction: Float = 1.2f, content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = pulseFraction,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(modifier = Modifier.scale(scale)) {
        content()
    }
}

