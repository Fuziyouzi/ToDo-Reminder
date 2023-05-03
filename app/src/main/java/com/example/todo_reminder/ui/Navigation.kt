package com.example.todo_reminder.ui


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            MainScreen(navController = navController)
        }
        composable(Screen.ToDoScreen.route) {
            ToDoScreen()
        }
        composable(Screen.SearchScreen.route){
            SearchScreen(onClick = { navController.popBackStack() })
        }
        composable(Screen.SettingsScreen.route) {
            SettingsScreen(onClick = { navController.popBackStack() })
        }
        composable(Screen.ManageCategories.route){
            ManageCategoriesScreen(backButton = { navController.popBackStack() })
        }
    }
}

