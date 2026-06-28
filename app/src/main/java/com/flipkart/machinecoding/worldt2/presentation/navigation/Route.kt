package com.flipkart.machinecoding.worldt2.presentation.navigation


sealed class Route(val route: String) {

    data object TeamSelection : Route("team_selection")

    data object MatchCenter : Route("match_center")
}