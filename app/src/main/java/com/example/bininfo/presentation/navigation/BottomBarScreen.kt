package com.example.bininfo.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Main : BottomBarScreen("main", "Главная", Icons.Filled.Search)
    object History : BottomBarScreen("history", "История", Icons.Filled.Menu)
}