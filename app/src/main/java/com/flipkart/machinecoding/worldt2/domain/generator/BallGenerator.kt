package com.flipkart.machinecoding.worldt2.domain.generator

import com.flipkart.machinecoding.worldt2.domain.model.BallOutcome

interface BallGenerator {
    fun nextBall(): BallOutcome
}