package com.flipkart.machinecoding.worldt2.domain.extensions

import com.flipkart.machinecoding.worldt2.domain.model.BallOutcome

fun BallOutcome.toDisplayText(): String {

    return when (this) {
        BallOutcome.Dot -> "0"
        BallOutcome.One -> "1"
        BallOutcome.Two -> "2"
        BallOutcome.Three -> "3"
        BallOutcome.Four -> "4"
        BallOutcome.Six -> "6"
        BallOutcome.Wicket -> "OUT"
        BallOutcome.Wide -> "WIDE"
        BallOutcome.NoBall -> "NO BALL"
    }
}