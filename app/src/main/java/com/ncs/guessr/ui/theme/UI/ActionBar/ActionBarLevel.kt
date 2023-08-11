package com.ncs.guessr.ui.theme.UI.ActionBar


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ncs.guessr.R
import com.ncs.guessr.utils.getTextValue

@Composable
fun LevelActionBar(onSettingsClicked: () -> Unit,onhelpClicked: () -> Unit,count:Int,score:Int){
    val context = LocalContext.current
    var helpCount by remember { mutableStateOf(getTextValue(context)) }
    Box (modifier = Modifier.fillMaxWidth()){
        Row (modifier = Modifier
            .height(90.dp)
            .fillMaxWidth()
            .padding(15.dp),horizontalArrangement = Arrangement.SpaceBetween, ){

            Box(modifier = Modifier
                .size(75.dp)
            ) {
                Box(modifier = Modifier.padding(start = 20.dp, top = 15.dp) ){
                    Box (
                        Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .width(65.dp)
                            .clickable {
                                onhelpClicked()
                            }
                            .background(Color.White)
                            .height(25.dp), contentAlignment = Alignment.Center
                    ){
                        Text(text = count.toString(), color = Color.Black, fontWeight = FontWeight.ExtraBold)
                    }
                }
                Image(painter = painterResource(id = R.drawable.bulb), modifier = Modifier.size(45.dp), contentDescription = "null")
            }
            Box(modifier = Modifier.padding(top = 10.dp)){
                Box (
                    Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .width(120.dp)
                        .background(Color.White)
                        .height(35.dp), contentAlignment = Alignment.Center
                ){
                    Text(text = "Score: ${score} ", color = Color.Black, fontWeight = FontWeight.ExtraBold)
                }
            }
            Box(modifier = Modifier.padding(top = 7.dp)){
                Image(painter = painterResource(id = R.drawable.settings), contentDescription ="null", modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        onSettingsClicked()
                    } )
            }
        }
    }
}
