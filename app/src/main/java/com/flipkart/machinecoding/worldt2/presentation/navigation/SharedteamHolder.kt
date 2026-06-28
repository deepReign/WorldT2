package com.flipkart.machinecoding.worldt2.presentation.navigation

import com.flipkart.machinecoding.worldt2.domain.model.Team // Adjust package as needed
import javax.inject.Inject
import javax.inject.Singleton

@Singleton // Use Singleton if you want the same selection to persist across the app session
class SelectedTeamsHolder @Inject constructor() {
    var team1: Team? = null
    var team2: Team? = null
}