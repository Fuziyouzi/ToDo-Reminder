package com.example.todo_reminder.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ToDoScreen(modifier: Modifier = Modifier, onBackPressed: () -> Unit){
    Column(modifier = modifier.fillMaxSize()) {
        TopBar(text = "toDoId.toString()", onClick = onBackPressed)
    }
}