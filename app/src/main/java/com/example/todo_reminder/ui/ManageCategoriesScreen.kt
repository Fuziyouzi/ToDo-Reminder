package com.example.todo_reminder.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_reminder.R
import javax.sql.RowSetWriter

@Composable
fun ManageCategoriesScreen(modifier: Modifier = Modifier, backButton: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        TopBar(text = "Manage Categories", onClick = backButton)
        LazyColumn(modifier = modifier.padding(horizontal = 6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)){
            items(listOfCategory, key = {it.category}){
                CategoryItem(text = it.category, onClick = { })
            }
        }
        CreateNew(onClick = { })

    }
}

@Composable
fun CategoryItem(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(modifier = modifier.size(26.dp), painter = painterResource(id = R.drawable.emoji), contentDescription = "Category Icon")
        Text(modifier = modifier.weight(1f).padding(start = 16.dp), text = text)
        IconButton(onClick = onClick) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More Category Item")
        }
    }
}

@Composable
fun CreateNew(modifier: Modifier = Modifier, onClick: () -> Unit){
    Row(modifier = modifier.padding(start = 6.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add New Category", tint = MaterialTheme.colors.secondary)
        TextButton(modifier = modifier.padding(start = 6.dp) ,onClick = onClick) {
            Text(text = "Create New", color = MaterialTheme.colors.secondary, fontSize = 18.sp)
        }
    }
}