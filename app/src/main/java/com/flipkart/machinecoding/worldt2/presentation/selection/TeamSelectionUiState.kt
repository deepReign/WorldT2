package com.flipkart.machinecoding.worldt2.presentation.selection

import com.flipkart.machinecoding.worldt2.domain.model.Team


data class TeamSelectionUiState(
    val isLoading: Boolean = false,
    val teams: List<Team> = emptyList(),
    val selectedTeams: List<Team> = emptyList(),
    val error: String? = null
)