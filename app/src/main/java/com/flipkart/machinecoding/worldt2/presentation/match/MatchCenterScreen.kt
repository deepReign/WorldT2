package com.flipkart.machinecoding.worldt2.presentation.match

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flipkart.machinecoding.worldt2.presentation.components.WorldT2Scaffold

@Composable
fun MatchCenterScreen(viewModel: MatchCenterViewModel = hiltViewModel()) {
    // This requires: androidx.lifecycle:lifecycle-runtime-compose
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WorldT2Scaffold(
        title = "Match Center",
        bottomButtonText = if (uiState.isMatchOver) "Match Over" else "Play Next Ball",
        onBottomButtonClick = {
            if (!uiState.isMatchOver) {
                viewModel.playNextBall()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            // Team 1 Row
            TeamInningsRow(
                teamName = uiState.team1Name,
                score = uiState.team1Score,
                wickets = uiState.team1Wickets,
                overs = uiState.team1Overs,
                statusLabel = uiState.team1Status,
                isYetToBat = false
            )

            HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)

            // Team 2 Row
            TeamInningsRow(
                teamName = uiState.team2Name,
                score = uiState.team2Score,
                wickets = uiState.team2Wickets,
                overs = uiState.team2Overs,
                statusLabel = uiState.team2Status,
                isYetToBat = uiState.isTeam2YetToBat
            )

            // Gray Box for Results
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFFD3D3D3)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.winnerMessage ?: uiState.lastOutcome,
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = if (uiState.winnerMessage != null) 32.sp else 64.sp
                    )
                )
            }
        }
    }
}

@Composable
fun TeamInningsRow(
    teamName: String,
    score: Int,
    wickets: Int,
    overs: String,
    statusLabel: String,
    isYetToBat: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "$teamName ($statusLabel)",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (isYetToBat) {
                Text(text = "yet to bat", color = Color.Gray)
                Text(text = "yet to bat", color = Color.Gray)
            } else {
                Text(text = "Score: $score/$wickets")
                Text(text = "Overs: $overs")
            }
        }
    }
}


