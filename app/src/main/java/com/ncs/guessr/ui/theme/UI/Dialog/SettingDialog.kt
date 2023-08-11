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
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ncs.guessr.R
import com.ncs.guessr.utils.getAllowVibrations
import com.ncs.guessr.utils.setAllowVibrations

@Composable
fun SettingsDialog(
    isSettingClicked: Boolean,
    onDismiss: () -> Unit,
    context: Context
) {
    var allowVibrations by remember { mutableStateOf(getAllowVibrations(context)) }

    if (isSettingClicked) {
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
                            .padding(start = 40.dp, top = 70.dp, end = 20.dp)
                    ) {

                        Column {
                            Row {
                                Image(painter = painterResource(id = R.drawable.vibrate), contentDescription ="null",
                                    Modifier
                                        .size(80.dp)
                                        .rotate(30F) )
                                Spacer(modifier = Modifier.width(70.dp))
                                Box (modifier = Modifier.padding(top=15.dp)){
                                    Switch(
                                        checked = allowVibrations,
                                        onCheckedChange = { allowVibrations= it
                                            setAllowVibrations(context, it)
                                        }
                                    )
                                }

                            }

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
