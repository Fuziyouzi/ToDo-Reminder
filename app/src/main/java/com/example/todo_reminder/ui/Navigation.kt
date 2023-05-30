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
            MainScreen(
                settings = { navController.navigate(Screen.SettingsScreen.route) },
                manageCategories = { navController.navigate(Screen.ManageCategories.route) },
                search = { navController.navigate(Screen.SearchScreen.route) },
                toDoScreen = { navController.navigate(Screen.ToDoScreen.route) }
            )
        }
        composable(
            route = Screen.ToDoScreen.route
        ) {
            ToDoScreen(onBackPressed = { navController.popBackStack() })
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(onClick = { navController.popBackStack() })
        }
        composable(Screen.SettingsScreen.route) {
            SettingsScreen(onClick = { navController.popBackStack() })
        }
        composable(Screen.ManageCategories.route) {
            ManageCategoriesScreen(backButton = { navController.popBackStack() })
        }
    }
}

