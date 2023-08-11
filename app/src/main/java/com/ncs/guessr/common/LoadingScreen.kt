package com.ncs.guessr.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun loadingscreen(){
    Box(modifier = Modifier
        .fillMaxSize(1f)
        .background(Color.Black), contentAlignment = Alignment.Center){
        Column {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()

            }
            Spacer(modifier = Modifier.height(25.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Text(text = "Please Wait! Loading the Content", color = Color.White)

            }
        }
    }
}