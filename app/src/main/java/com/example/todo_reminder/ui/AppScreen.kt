package com.example.todo_reminder.ui


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.todo_reminder.ui.theme.ToDoReminderTheme

@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    ToDoReminderTheme {
        val navHostController = rememberNavController()

        Surface(modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

            Navigation(modifier = modifier, navController = navHostController)
        }
    }

}