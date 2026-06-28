package com.flipkart.machinecoding.worldt2.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorldT2Scaffold(
    title: String,
    onBackClick: (() -> Unit)? = null,
    bottomButtonText: String? = null,
    isBottomButtonEnabled: Boolean = true,
    onBottomButtonClick: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = title, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
                // You can add navigationIcon here if onBackClick is not null
            )
        },
        bottomBar = {
            if (bottomButtonText != null) {
                Button(
                    onClick = onBottomButtonClick,
                    enabled = isBottomButtonEnabled,
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.navigationBars) // Handles bottom nav bar overlap
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1B5E20), // Standard Cricket Green
                        disabledContainerColor = Color.Gray
                    )
                ) {
                    Text(text = bottomButtonText, color = Color.White)
                }
            }
        },
        content = { paddingValues ->
            // We pass paddingValues to the screen content to avoid status bar overlap
            content(paddingValues)
        }
    )
}