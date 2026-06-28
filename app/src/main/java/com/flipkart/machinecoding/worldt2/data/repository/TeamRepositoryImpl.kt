package com.flipkart.machinecoding.worldt2.data.repository

import android.content.Context
import com.flipkart.machinecoding.worldt2.data.dto.TeamDto
import com.flipkart.machinecoding.worldt2.data.mapper.toDomain
import com.flipkart.machinecoding.worldt2.domain.model.Team
import com.flipkart.machinecoding.worldt2.domain.repository.TeamRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) : TeamRepository {

    override suspend fun getTeams(): Result<List<Team>> = withContext(Dispatchers.IO) {
        try {
            val jsonString = context.assets.open("teams.json").bufferedReader().use { it.readText() }
            val listType = object : TypeToken<List<TeamDto>>() {}.type
            val teamDtos: List<TeamDto> = gson.fromJson(jsonString, listType)
            val teams = teamDtos.map { it.toDomain() }
            Result.success(teams)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
