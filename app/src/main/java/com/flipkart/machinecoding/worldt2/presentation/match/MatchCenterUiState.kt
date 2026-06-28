package com.flipkart.machinecoding.worldt2.presentation.match

data class MatchCenterUiState(
    val team1Name: String = "",
    val team2Name: String = "",
    val team1Score: Int = 0,
    val team1Wickets: Int = 0,
    val team1Overs: String = "0.0",
    val team2Score: Int = 0,
    val team2Wickets: Int = 0,
    val team2Overs: String = "0.0",
    val team1Status: String = "Batting", // "Batting", "Bowling", or "Completed"
    val team2Status: String = "Bowling",
    val isTeam2YetToBat: Boolean = true,
    val lastOutcome: String = "-",
    // --- ADD THIS LINE ---
    val isFirstInningsOver: Boolean = false,
    // ---------------------
    val isMatchOver: Boolean = false,
    val winnerMessage: String? = null
)