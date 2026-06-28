package com.flipkart.machinecoding.worldt2.domain.model

sealed interface BallOutcome {
    data object Dot : BallOutcome
    data object One : BallOutcome
    data object Two : BallOutcome
    data object Three : BallOutcome
    data object Four : BallOutcome
    data object Six : BallOutcome
    data object Wicket : BallOutcome
    data object Wide : BallOutcome
    data object NoBall : BallOutcome
}