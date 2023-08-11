package com.ncs.guessr.ui.theme.UI


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import com.ncs.guessr.R
import com.ncs.guessr.firebase.RealTimeModelResponse
import com.ncs.guessr.ui.theme.EndScreen
import com.ncs.guessr.ui.theme.zeroScoreScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LevelHosting(data:  List<RealTimeModelResponse>,navController: NavController) {
    val context = LocalContext.current
    var updatedhelpCount by remember { mutableStateOf(getTextValue(context)) }
    var score by remember { mutableStateOf(0) }
    var isSettingDialogVisible by remember { mutableStateOf(false) }
    var levelcompleted by remember { mutableStateOf(false) }
    var ishelpDialogVisible by remember { mutableStateOf(false) }
    var currentIndex by remember { mutableStateOf(0) }
    var correctDialog by remember { mutableStateOf(false) }
    var wrongDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize() ){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)) {
            if (levelcompleted){
                showendScreen(score = score,navController)
            }

            LevelActionBar(onSettingsClicked = {isSettingDialogVisible=true}, onhelpClicked = {ishelpDialogVisible=true}, count = updatedhelpCount,score=score)
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize(1f)) {

                    ques_body(levelData = data[currentIndex].item!! ) { isCorrectAnswer ->
                        if (isCorrectAnswer) {
                            score += 10
                            correctDialog = true
                            coroutineScope.launch {
                                delay(2000)
                                correctDialog = false
                                if (currentIndex < data.size - 1) {
                                    currentIndex++
                                }
                                else{
                                    currentIndex=data.size-1
                                    levelcompleted=true
                                }
                            }
                        }  else {
                            wrongDialog = true
                            coroutineScope.launch {
                                delay(2000)
                                wrongDialog = false
                                if (currentIndex < data.size - 1) {
                                    currentIndex++
                                }
                                else{
                                    currentIndex=data.size-1
                                    levelcompleted=true
                                }
                            }
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
fun ques_body(levelData: RealTimeModelResponse.RealTimeItems ,onAnswerSelected: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .height(520.dp)
            .background(Color.Black)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//            Image(painter = painterResource(levelData.image), contentDescription = "logo", Modifier.size(150.dp))
            AsyncImage(
                model = levelData.image["url"].toString(),
                contentDescription = null,
                modifier = Modifier.size(90.dp)
            )

        }

        Box(modifier = Modifier.padding(top = 40.dp)) {

            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {

                for (i in 0..3) {
                    optionList(levelData.options, i,onAnswerSelected, levelData)
                }
            }
        }
    }
}
@Composable
fun optionList(
    levelData: List<String?>,
    i: Int,
    onAnswerSelected: (Boolean) -> Unit,
    levelData1: RealTimeModelResponse.RealTimeItems
) {
    val answer = levelData1.answer
    val coroutineScope = rememberCoroutineScope()
    var selectedOption by remember { mutableStateOf(-1) }
    val backgroundColor by animateColorAsState(
        targetValue = if (selectedOption == i) {
            if (answer == levelData[i]) {
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
                    onAnswerSelected(answer == levelData[i])
                    selectedOption = -1
                }
            }
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Text(
                text = levelData[i]!!,
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
fun showendScreen(score: Int,navController: NavController) {
    if (score > 0) {
        EndScreen(score = score,navController)
    } else {
        zeroScoreScreen(navController)
    }
}

