package com.flipkart.machinecoding.worldt2.domain.model

sealed interface MatchResult {
    data object Team1Won : MatchResult
    data object Team2Won : MatchResult
    data object Tie : MatchResult
    data object Ongoing : MatchResult
}