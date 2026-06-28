package com.flipkart.machinecoding.worldt2.domain.engine

import com.flipkart.machinecoding.worldt2.domain.extensions.toDisplayText
import com.flipkart.machinecoding.worldt2.domain.generator.BallGenerator
import com.flipkart.machinecoding.worldt2.domain.model.BallOutcome
import com.flipkart.machinecoding.worldt2.domain.model.Innings
import com.flipkart.machinecoding.worldt2.domain.model.MatchState
import com.flipkart.machinecoding.worldt2.domain.model.Team


class MatchEngine(
    private val ballGenerator: BallGenerator
) {

    private lateinit var team1: Team
    private lateinit var team2: Team
    private var firstInnings = Innings()
    private var secondInnings = Innings()
    private var inningsNumber = 1
    private var matchOver = false
    private var lastOutcome = ""

    fun startMatch(
        battingFirst: Team,
        bowlingFirst: Team
    ) {

        team1 = battingFirst
        team2 = bowlingFirst

        firstInnings = Innings()

        secondInnings = Innings()

        inningsNumber = 1

        matchOver = false

        lastOutcome = ""
    }

    fun playBall(): MatchState {

        if (matchOver) {
            return currentState()
        }

        val outcome = ballGenerator.nextBall()

        lastOutcome = outcome.toDisplayText()

        when (inningsNumber) {

            1 -> processFirstInnings(outcome)

            2 -> processSecondInnings(outcome)
        }

        return currentState()
    }

    private fun processFirstInnings(
        outcome: BallOutcome
    ) {

        firstInnings = updateInnings(
            firstInnings,
            outcome
        )

        if (
            firstInnings.legalBalls >= 12 ||
            firstInnings.wickets >= 3
        ) {

            inningsNumber = 2
        }
    }

    private fun processSecondInnings(
        outcome: BallOutcome
    ) {

        secondInnings = updateInnings(
            secondInnings,
            outcome
        )

        val target = firstInnings.runs + 1

        if (secondInnings.runs >= target) {

            matchOver = true
            return
        }

        if (
            secondInnings.legalBalls >= 12 ||
            secondInnings.wickets >= 3
        ) {

            matchOver = true
        }
    }

    private fun updateInnings(
        innings: Innings,
        outcome: BallOutcome
    ): Innings {

        return when (outcome) {

            BallOutcome.Dot -> {
                innings.copy(
                    legalBalls = innings.legalBalls + 1
                )
            }

            BallOutcome.One -> {
                innings.copy(
                    runs = innings.runs + 1,
                    legalBalls = innings.legalBalls + 1
                )
            }

            BallOutcome.Two -> {
                innings.copy(
                    runs = innings.runs + 2,
                    legalBalls = innings.legalBalls + 1
                )
            }

            BallOutcome.Three -> {
                innings.copy(
                    runs = innings.runs + 3,
                    legalBalls = innings.legalBalls + 1
                )
            }

            BallOutcome.Four -> {
                innings.copy(
                    runs = innings.runs + 4,
                    legalBalls = innings.legalBalls + 1
                )
            }

            BallOutcome.Six -> {
                innings.copy(
                    runs = innings.runs + 6,
                    legalBalls = innings.legalBalls + 1
                )
            }

            BallOutcome.Wicket -> {
                innings.copy(
                    wickets = innings.wickets + 1,
                    legalBalls = innings.legalBalls + 1
                )
            }

            BallOutcome.Wide -> {
                innings.copy(
                    runs = innings.runs + 1
                )
            }

            BallOutcome.NoBall -> {
                innings.copy(
                    runs = innings.runs + 1
                )
            }
        }
    }

    private fun currentState(): MatchState {

        val innings =
            if (inningsNumber == 1)
                firstInnings
            else
                secondInnings

        return MatchState(

            battingTeam =
                if (inningsNumber == 1)
                    team1
                else
                    team2,

            bowlingTeam =
                if (inningsNumber == 1)
                    team2
                else
                    team1,

            currentInnings = inningsNumber,

            battingRuns = innings.runs,

            battingWickets = innings.wickets,

            battingBalls = innings.legalBalls,

            target =
                if (inningsNumber == 2)
                    firstInnings.runs + 1
                else
                    null,

            firstInningsScore =
                if (inningsNumber == 2)
                    firstInnings.runs
                else
                    null,

            lastOutcome = lastOutcome,

            isMatchOver = matchOver,

            winnerMessage =
                if (matchOver)
                    getWinnerMessage()
                else
                    null
        )
    }

    private fun getWinnerMessage(): String {

        return when {

            secondInnings.runs > firstInnings.runs ->
                "${team2.name} Won"

            secondInnings.runs < firstInnings.runs ->
                "${team1.name} Won"

            else ->
                "Match Tied"
        }
    }
}