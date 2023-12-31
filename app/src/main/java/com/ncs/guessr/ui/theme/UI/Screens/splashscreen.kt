package com.ncs.guessr.ui.theme.UI.Screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ncs.guessr.R
import kotlinx.coroutines.delay

@Composable
fun splashScreen(navController: NavController){

    var scale= remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 1f, animationSpec = tween(1500, easing ={
            OvershootInterpolator(5f).getInterpolation(it)
        } ))
        delay(3000L)
        navController.navigate("start")
    }
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color.Black), contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "null", modifier = Modifier.scale(scale.value))
    }
}
