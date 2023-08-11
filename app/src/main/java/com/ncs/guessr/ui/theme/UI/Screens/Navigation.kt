package com.ncs.guessr.ui.theme.UI.Screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(){

    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "splash"){
        composable("splash"){
            splashScreen(navController = navController)
        }
        composable("start"){
            start(navController = navController)
        }
        composable("homescreen"){
            homeScreen(navController = navController)
        }
        composable("Newbie"){
            newbieLevel(navController = navController)
        }
        composable("Moderate"){
            moderateLevel(navController = navController)
        }
        composable("Master"){
            masterLevel(navController = navController)
        }
    }
}