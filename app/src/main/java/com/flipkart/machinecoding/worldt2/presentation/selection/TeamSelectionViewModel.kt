package com.flipkart.machinecoding.worldt2.presentation.selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flipkart.machinecoding.worldt2.domain.model.Team
import com.flipkart.machinecoding.worldt2.domain.repository.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamSelectionViewModel @Inject constructor(
    private val repository: TeamRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TeamSelectionUiState())
    val uiState: StateFlow<TeamSelectionUiState> = _uiState.asStateFlow()

    init {
        loadTeams()
    }

    private fun loadTeams() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)

            }

            repository.getTeams()
                .onSuccess { teams ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            teams = teams
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message ?: "Failed to load teams"
                        )
                    }
                }

        }
    }

    fun onTeamClicked(team: Team) {
        val current = _uiState.value.selectedTeams.toMutableList()
        if (current.contains(team)) {
            current.remove(team)
        } else if (current.size < 2) {
            current.add(team)
        }
        _uiState.update { it.copy(selectedTeams = current) }
    }
}
