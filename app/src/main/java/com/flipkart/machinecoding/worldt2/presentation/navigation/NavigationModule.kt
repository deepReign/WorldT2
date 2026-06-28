package com.flipkart.machinecoding.worldt2.presentation.navigation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun provideSelectedTeamsHolder() =
        SelectedTeamsHolder()
}