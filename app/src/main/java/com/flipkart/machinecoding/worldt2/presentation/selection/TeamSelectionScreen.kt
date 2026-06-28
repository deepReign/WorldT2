package com.flipkart.machinecoding.worldt2.presentation.selection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flipkart.machinecoding.worldt2.domain.model.Team
import com.flipkart.machinecoding.worldt2.presentation.components.TeamCard
import com.flipkart.machinecoding.worldt2.presentation.components.WorldT2Scaffold

@Composable
fun TeamSelectionScreen(
    onStartMatch: (Team, Team) -> Unit,
    viewModel: TeamSelectionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WorldT2Scaffold(
        title = "Select Teams",
        bottomButtonText = "Start Match",
        isBottomButtonEnabled = uiState.selectedTeams.size == 2,
        onBottomButtonClick = {
            if (uiState.selectedTeams.size == 2) {
                onStartMatch(
                    uiState.selectedTeams[0],
                    uiState.selectedTeams[1]
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.error != null -> {
                    Text(
                        text = uiState.error ?: "Unknown Error",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            Text(
                                text = "Select Two Teams",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(16.dp)
                            )
                        }

                        items(
                            items = uiState.teams,
                            key = { it.name }
                        ) { team ->
                            TeamCard(
                                team = team,
                                selected = uiState.selectedTeams.contains(team),
                                onClick = {
                                    viewModel.onTeamClicked(team)
                                }
                            )
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                thickness = 0.5.dp,
                                color = MaterialTheme.colorScheme.outlineVariant
                            )
                        }
                    }
                }
            }
        }
    }


}