package com.flipkart.machinecoding.worldt2.presentation.match

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.flipkart.machinecoding.worldt2.domain.model.BallOutcome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MatchCenterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val team1Name: String = savedStateHandle.get<String>("team1Name") ?: "Team A"
    private val team2Name: String = savedStateHandle.get<String>("team2Name") ?: "Team B"

    private val _uiState = MutableStateFlow(
        MatchCenterUiState(
            team1Name = team1Name,
            team2Name = team2Name,
            team1Status = "Batting",
            team2Status = "Bowling"
        )
    )
    val uiState: StateFlow<MatchCenterUiState> = _uiState.asStateFlow()

    private var t1Balls = 0
    private var t2Balls = 0

    fun playNextBall() {
        if (_uiState.value.isMatchOver) return

        val outcome = generateRandomOutcome()

        // Map the sealed interface object to numerical data
        val runs = getRuns(outcome)
        val isWicket = outcome is BallOutcome.Wicket
        val isExtra = outcome is BallOutcome.Wide || outcome is BallOutcome.NoBall
        val displayValue = getDisplayValue(outcome)

        if (!_uiState.value.isFirstInningsOver) {
            handleFirstInnings(runs, isWicket, isExtra, displayValue)
        } else {
            handleSecondInnings(runs, isWicket, isExtra, displayValue)
        }
    }



    private fun handleFirstInnings(runs: Int, isWicket: Boolean, isExtra: Boolean, display: String) {
        _uiState.update { state ->
            val newRuns = state.team1Score + runs
            val newWickets = if (isWicket) state.team1Wickets + 1 else state.team1Wickets
            if (!isExtra) t1Balls++

            val isOver = t1Balls >= 12 || newWickets >= 3

            state.copy(
                team1Score = newRuns,
                team1Wickets = newWickets,
                team1Overs = formatOvers(t1Balls),
                lastOutcome = display,
                isFirstInningsOver = isOver,
                team1Status = if (isOver) "Completed" else "Batting",
                team2Status = if (isOver) "Batting" else "Bowling",
                isTeam2YetToBat = !isOver
            )
        }
    }

    private fun handleSecondInnings(runs: Int, isWicket: Boolean, isExtra: Boolean, display: String) {
        _uiState.update { state ->
            val newRuns = state.team2Score + runs
            val newWickets = if (isWicket) state.team2Wickets + 1 else state.team2Wickets
            if (!isExtra) t2Balls++

            val outscored = newRuns > state.team1Score
            val allBallsPlayed = t2Balls >= 12
            val allWicketsLost = newWickets >= 3

            val isMatchFinished = outscored || allBallsPlayed || allWicketsLost

            var winner: String? = null
            if (isMatchFinished) {
                winner = when {
                    newRuns > state.team1Score -> "$team2Name Wins"
                    newRuns < state.team1Score -> "$team1Name Wins"
                    else -> "Match Tied"
                }
            }

            state.copy(
                team2Score = newRuns,
                team2Wickets = newWickets,
                team2Overs = formatOvers(t2Balls),
                lastOutcome = display,
                isMatchOver = isMatchFinished,
                winnerMessage = winner,
                team2Status = if (isMatchFinished) "Completed" else "Batting"
            )
        }
    }

    private fun getRuns(outcome: BallOutcome): Int = when (outcome) {
        BallOutcome.Dot -> 0
        BallOutcome.One -> 1
        BallOutcome.Two -> 2
        BallOutcome.Three -> 3
        BallOutcome.Four -> 4
        BallOutcome.Six -> 6
        BallOutcome.Wide -> 1
        BallOutcome.NoBall -> 1
        BallOutcome.Wicket -> 0
    }

    private fun getDisplayValue(outcome: BallOutcome): String = when (outcome) {
        BallOutcome.Dot -> "0"
        BallOutcome.One -> "1"
        BallOutcome.Two -> "2"
        BallOutcome.Three -> "3"
        BallOutcome.Four -> "4"
        BallOutcome.Six -> "6"
        BallOutcome.Wicket -> "Out"
        BallOutcome.Wide -> "Wide"
        BallOutcome.NoBall -> "No Ball"
    }

    private fun generateRandomOutcome(): BallOutcome {
        val rand = Random.nextInt(100)
        return when {
            rand < 5 -> BallOutcome.Wide
            rand < 10 -> BallOutcome.NoBall
            rand < 20 -> BallOutcome.Wicket
            rand < 30 -> BallOutcome.Six
            rand < 45 -> BallOutcome.Four
            rand < 55 -> BallOutcome.Three
            rand < 70 -> BallOutcome.Two
            rand < 85 -> BallOutcome.One
            else -> BallOutcome.Dot
        }
    }

    private fun formatOvers(balls: Int): String = "${balls / 6}.${balls % 6}"
}