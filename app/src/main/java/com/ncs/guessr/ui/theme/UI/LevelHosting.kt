package com.ncs.guessr.ui.theme.UI


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ncs.guessr.LevelData
import com.ncs.guessr.R
import com.ncs.guessr.leveltypes
import com.ncs.guessr.ui.theme.EndScreen
import com.ncs.guessr.ui.theme.zeroScoreScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Wrapper

@Composable
fun LevelHosting(data:MutableList<LevelData>) {
    val context = LocalContext.current
    var updatedhelpCount by remember { mutableStateOf(getTextValue(context)) }
    var score by remember { mutableStateOf(0) }
    var isSettingDialogVisible by remember { mutableStateOf(false) }
    var levelcompleted by remember { mutableStateOf(false) }
    var ishelpDialogVisible by remember { mutableStateOf(false) }
    var attemptCount by remember { mutableStateOf(0) }
    var currentIndex by remember { mutableStateOf(0) }
    var correctDialog by remember { mutableStateOf(false) }
    var wrongDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize() ){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)) {
            if (levelcompleted){
                showendScreen(score = score)
            }

            LevelActionBar(onSettingsClicked = {isSettingDialogVisible=true}, onhelpClicked = {ishelpDialogVisible=true}, count = updatedhelpCount,score=score)
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize(1f)) {

                    ques_body(levelData = data[currentIndex]) { isCorrectAnswer ->
                        if (isCorrectAnswer) {
                            score += 10
                            correctDialog = true
                            coroutineScope.launch {
                                delay(2000)
                                correctDialog = false
                                if (currentIndex < data.size - 1) {
                                    currentIndex++
                                    attemptCount = 0
                                }
                                else{
                                    currentIndex=data.size-1
                                    levelcompleted=true
                                }
                                attemptCount = 0
                            }
                        } else if (attemptCount <1) {
                            attemptCount++
                            wrongDialog = true
                            coroutineScope.launch {
                                delay(2000)
                                wrongDialog = false
                            }
                        } else {
                            currentIndex++
                            attemptCount = 0
                        }
                    }
                    Box(modifier = Modifier.padding(start = 30.dp, top = 30.dp)) {
                        if (updatedhelpCount > 0) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.skip),
                                    contentDescription = "skip",
                                    modifier = Modifier
                                        .size(45.dp)
                                        .clickable {
                                            updatedhelpCount--
                                            saveTextValue(context, updatedhelpCount)
                                            if (currentIndex < data.size - 1) {
                                                currentIndex++
                                            } else {
                                                currentIndex = data.size - 1
                                                levelcompleted = true
                                            }

                                        }
                                )
                                Box (modifier = Modifier.padding(top = 10.dp)){
                                    Row {
                                        Text(text = "-1 ", color = Color.White, fontSize = 22.sp)
                                        Image(painter = painterResource(id = R.drawable.bulb), modifier = Modifier.size(25.dp), contentDescription = "null")

                                    }
                                }

                            }

                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.skip_dis),
                                contentDescription = "skip_disabled",
                                modifier = Modifier.size(45.dp)
                            )
                        }
                    }
                }
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    if (correctDialog) {
                        correct(context = LocalContext.current)
                        CorrectDialog()
                    }
                    if (wrongDialog) {
                        wrong(context = LocalContext.current)
                        WrongDialog()
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
    }

}


@Composable
fun ques_body(levelData: LevelData,onAnswerSelected: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .height(520.dp)
            .background(Color.Black)
    ) {
        Box(modifier = Modifier.padding(start = 115.dp)) {
            Image(painter = painterResource(levelData.logo), contentDescription = "logo", Modifier.size(150.dp))

        }

        Box(modifier = Modifier.padding(top = 40.dp)) {

            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {

                for (i in 0..3) {
                    optionList(levelData, i,onAnswerSelected)
                }
            }
        }
    }
}
@Composable
fun optionList(levelData: LevelData, i: Int,onAnswerSelected: (Boolean) -> Unit) {
    val answer = levelData.answer
    val coroutineScope = rememberCoroutineScope()
    var selectedOption by remember { mutableStateOf(-1) }
    val backgroundColor by animateColorAsState(
        targetValue = if (selectedOption == i) {
            if (answer == levelData.options[i]) {
                Color.Green

            } else {
                Color.Red
            }
        } else {
            Color.White
        },
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 10.dp, bottom = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                selectedOption = i
                coroutineScope.launch {
                    delay(200)
                    onAnswerSelected(answer == levelData.options[i])
                    selectedOption = -1
                }
            }
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Text(
                text = levelData.options[i],
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
@Composable
fun CorrectDialog() {
    anim()
}
@Composable
fun WrongDialog() {
    anim2()
}
@Composable
fun showendScreen(score: Int) {
    if (score > 0) {
        EndScreen(score = score)
    } else {
        zeroScoreScreen()
    }
}

