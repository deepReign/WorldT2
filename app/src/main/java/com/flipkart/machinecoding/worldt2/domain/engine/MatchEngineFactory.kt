package com.flipkart.machinecoding.worldt2.domain.engine

interface MatchEngineFactory {
    fun create(): MatchEngine
}