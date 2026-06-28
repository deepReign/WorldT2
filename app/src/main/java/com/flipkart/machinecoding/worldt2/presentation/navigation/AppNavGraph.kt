package com.flipkart.machinecoding.worldt2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.flipkart.machinecoding.worldt2.presentation.match.MatchCenterScreen
import com.flipkart.machinecoding.worldt2.presentation.selection.TeamSelectionScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "selection"
    ) {
        // Screen 1: Selection
        composable("selection") {
            TeamSelectionScreen(
                onStartMatch = { team1, team2 ->
                    // Navigate using the names as path arguments
                    navController.navigate("match_center/${team1.name}/${team2.name}")
                }
            )
        }

        // Screen 2: Match Center
        composable(
            route = "match_center/{team1Name}/{team2Name}",
            arguments = listOf(
                navArgument("team1Name") { type = NavType.StringType },
                navArgument("team2Name") { type = NavType.StringType }
            )
        ) {
            MatchCenterScreen()
        }
    }
}