package com.flipkart.machinecoding.worldt2.domain.engine

import com.flipkart.machinecoding.worldt2.domain.generator.BallGenerator
import javax.inject.Inject

class MatchEngineFactoryImpl @Inject constructor(
    private val generator: BallGenerator
) : MatchEngineFactory {

    override fun create(): MatchEngine {
        return MatchEngine(generator)
    }
}