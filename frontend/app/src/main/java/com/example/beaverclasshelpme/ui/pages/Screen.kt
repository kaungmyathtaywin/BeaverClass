package com.example.beaverclasshelpme.ui.pages

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector) {
    object SignIn: Screen("signin", Icons.Default.AccountCircle)
    object SignUp: Screen("signup", Icons.Default.AccountCircle)
    object Home: Screen("home", Icons.Default.Home)
    object Cart: Screen("cart", Icons.Default.Menu)
    object Settings: Screen("settings", Icons.Default.AccountCircle)
    object SearchResult: Screen("searchResult", Icons.Default.List)
    object DraftEmail: Screen("draftEmail", Icons.Default.Email)
}