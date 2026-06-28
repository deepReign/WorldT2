package com.flipkart.machinecoding.worldt2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.flipkart.machinecoding.worldt2.presentation.navigation.AppNavGraph
import com.flipkart.machinecoding.worldt2.presentation.navigation.SelectedTeamsHolder
import com.flipkart.machinecoding.worldt2.ui.theme.WorldT2Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorldT2Theme {
                AppNavGraph()
            }
        }
    }
}