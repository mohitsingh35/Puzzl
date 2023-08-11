package com.ncs.guessr.ui.theme.UI.Dialog

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncs.guessr.R
import com.ncs.guessr.utils.getTextValue

@Composable
fun helpDialog(
    ishelpclicked: Boolean,
    onDismiss: () -> Unit,
    context: Context
) {
    var helpCount by remember { mutableStateOf(getTextValue(context)) }


    if (ishelpclicked) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black,
                            Color.Transparent
                        )
                    )
                )
            , contentAlignment = Alignment.Center
        ) {

            Box( modifier = Modifier
                .height(450.dp)
                .width(400.dp)
            ) {

                Box (modifier = Modifier
                    .height(400.dp)
                    .width(400.dp), contentAlignment = Alignment.Center){
                    Box(
                        modifier = Modifier
                            .height(350.dp)
                            .width(300.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .padding(start = 30.dp, top = 50.dp, end = 20.dp)
                    ) {

                        Column {
                            Box (modifier = Modifier.padding(start = 15.dp)){
                                Row {
                                    Text(text = "Available helps: ", color = Color.Black, fontSize = 22.sp)
                                    Text(text = helpCount.toString(), color = Color.Black, fontSize = 22.sp)
                                    Image(painter = painterResource(id = R.drawable.bulb), modifier = Modifier.size(25.dp), contentDescription = "null")
                                }
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                                Text(text = "Hey! \uD83D\uDC4B got stuck some where? \n\nUse the Skip button to skip the current level, but remember using a skip button once takes your 1 help. ", color = Color.Black, textAlign = TextAlign.Center)

                        }
                    }
                }

                Box(Modifier.padding(start = 260.dp)) {
                    Box(modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .size(60.dp)
                        .background(Color.Red)
                        .clickable { onDismiss() }
                        .padding(5.dp), contentAlignment = Alignment.Center) {
                        Image(painter = painterResource(id = R.drawable.close), contentDescription = "null",
                            Modifier.size(45.dp) )
                    }
                }
            }
        }
    }
}
