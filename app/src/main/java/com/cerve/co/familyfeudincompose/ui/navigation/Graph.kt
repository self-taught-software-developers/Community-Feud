package com.cerve.co.familyfeudincompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cerve.co.familyfeudincompose.ui.FamilyFeudViewModel
import com.cerve.co.familyfeudincompose.ui.navigation.Route.LANDING
import com.cerve.co.familyfeudincompose.ui.navigation.Route.MAIN_GAME
import com.cerve.co.familyfeudincompose.ui.navigation.Route.TEAM_CREATION
import com.cerve.co.familyfeudincompose.ui.navigation.Route.WINNER

@Composable
fun Graph(
    vm: FamilyFeudViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = LANDING
    ) {

        composable(LANDING) {

        }

        composable(TEAM_CREATION) {

        }

        composable(MAIN_GAME) {

        }

        composable(WINNER) {

        }

    }

}