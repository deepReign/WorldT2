package com.flipkart.machinecoding.worldt2.di

import com.flipkart.machinecoding.worldt2.domain.engine.MatchEngineFactory
import com.flipkart.machinecoding.worldt2.domain.engine.MatchEngineFactoryImpl
import com.flipkart.machinecoding.worldt2.domain.generator.BallGenerator
import com.flipkart.machinecoding.worldt2.domain.generator.WeightedBallGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MatchModule {

    @Provides
    fun provideBallGenerator(): BallGenerator {
        return WeightedBallGenerator()
    }

    @Provides
    fun provideFactory(
        generator: BallGenerator
    ): MatchEngineFactory {
        return MatchEngineFactoryImpl(generator)
    }
}