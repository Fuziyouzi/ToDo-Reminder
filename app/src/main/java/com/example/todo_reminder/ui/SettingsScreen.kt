package com.example.todo_reminder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_reminder.ui.theme.Shapes

@Composable
fun SettingsScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        TopBar(text = "Search", onClick = onClick )

        SettingsItem(modifier, { }, text = "Manage Notification")
        SettingsItem(modifier, { }, text = "Version")
        SettingsItem(modifier, { }, text = "About Notes")
        SettingsItem(modifier, { }, text = "Privacy")


    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier, text: String, onClick: () -> Unit){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colors.onPrimary
            )
        }
        Text(text = text, fontSize = 18.sp, color = MaterialTheme.colors.onPrimary)
    }
}

@Composable
fun SettingsItem(modifier: Modifier = Modifier, onClick: () -> Unit, text: String) {

    Surface(
        modifier = modifier
            .padding(top = 10.dp),
        shape = Shapes.medium.copy(CornerSize(35)),
        color = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = modifier
                .clickable { onClick }
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                color = MaterialTheme.colors.onPrimary
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "ArrowForward",
                tint = MaterialTheme.colors.onSecondary
            )
        }
    }

}