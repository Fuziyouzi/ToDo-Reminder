package com.example.todo_reminder.ui

sealed class Screen(val route: String){
    object HomeScreen: Screen("home_screen")
    object ToDoScreen: Screen("to_do_screen")
    object SearchScreen: Screen("search_screen")
    object SettingsScreen: Screen("setting_screen")
    object ManageCategories: Screen("manage_categories")

}
