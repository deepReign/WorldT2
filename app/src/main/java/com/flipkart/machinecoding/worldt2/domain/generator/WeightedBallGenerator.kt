package com.flipkart.machinecoding.worldt2.domain.generator

import com.flipkart.machinecoding.worldt2.domain.model.BallOutcome
import javax.inject.Inject
import kotlin.random.Random

class WeightedBallGenerator @Inject constructor() : BallGenerator {
    private val weightedOutcomes =
        listOf(
            BallOutcome.Dot to 25,
            BallOutcome.One to 30,
            BallOutcome.Two to 15,
            BallOutcome.Three to 5,
            BallOutcome.Four to 12,
            BallOutcome.Six to 5,
            BallOutcome.Wicket to 5,
            BallOutcome.Wide to 2,
            BallOutcome.NoBall to 1
        )

    override fun nextBall(): BallOutcome {

        val totalWeight =
            weightedOutcomes.sumOf { it.second }

        val random =
            Random.nextInt(totalWeight)

        var cumulative = 0

        weightedOutcomes.forEach { entry ->

            cumulative += entry.second

            if (random < cumulative) {
                return entry.first
            }
        }

        return BallOutcome.Dot
    }
}