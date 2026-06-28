package com.flipkart.machinecoding.worldt2.domain.repository

import com.flipkart.machinecoding.worldt2.domain.model.Team

interface TeamRepository {
    suspend fun getTeams(): Result<List<Team>>
}
