package com.flipkart.machinecoding.worldt2.di

import android.content.Context
import com.flipkart.machinecoding.worldt2.data.repository.TeamRepositoryImpl
import com.flipkart.machinecoding.worldt2.domain.repository.TeamRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideRepository(
        @ApplicationContext context: Context,
        gson: Gson
    ): TeamRepository {
        return TeamRepositoryImpl(context, gson)
    }
}
