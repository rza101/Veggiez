package com.rhezarijaya.veggiez.ui.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Detail : Screen("detail/{id}")
    object Favorite : Screen("favorite")
    object About : Screen("about")
}