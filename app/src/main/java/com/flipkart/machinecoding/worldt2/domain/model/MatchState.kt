package com.flipkart.machinecoding.worldt2.domain.model


data class MatchState(

    val battingTeam: Team,
    val bowlingTeam: Team,
    val currentInnings: Int,
    val battingRuns: Int,
    val battingWickets: Int,
    val battingBalls: Int,
    val target: Int?,
    val firstInningsScore: Int?,
    val lastOutcome: String,
    val isMatchOver: Boolean,
    val winnerMessage: String?
)