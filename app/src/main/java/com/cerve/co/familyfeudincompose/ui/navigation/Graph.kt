package com.cerve.co.familyfeudincompose.ui.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
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
import com.cerve.co.familyfeudincompose.ui.screen.MainGamingScreen
import com.cerve.co.familyfeudincompose.ui.screen.TeamCreationScreen
import com.cerve.co.familyfeudincompose.ui.screen.WinnerScreen

@Composable
fun Graph(
    startDestination: String = TEAM_CREATION,
    vm: FamilyFeudViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(LANDING) {

        }

        composable(TEAM_CREATION) {

            val teams by vm.createdTeams.collectAsState()

            TeamCreationScreen(
                teamList = teams,
                onNavigate = { navController.navigate(MAIN_GAME) },
                onDeleteClick = vm::deleteTeamAtIndex,
                onClick = vm::createNewTeam
            )

        }

        composable(MAIN_GAME) {

            val strikes by vm.strikeAccumulation.collectAsState()
            val team by vm.teamNowPlaying.collectAsState()
            val question by vm.currentQuestion.collectAsState()

            MainGamingScreen(
                question = question,
                questionCardList = vm.questions,
                onSelectQuestion = vm::selectQuestion,
                currentTeam = team,
                onAwardPoints = vm::awardPoints,
                onNextTeam = vm::nextTeam,
                strikes = strikes,
                onAddStrike = vm::addStrike
            ) { navController.navigate(WINNER) }

        }

        composable(WINNER) {
            val winner by vm.winningTeam.collectAsState()

            WinnerScreen(team = winner) {
                vm.resetPoints().also {
                    navController.navigate(TEAM_CREATION)
                }
            }

        }

    }

}