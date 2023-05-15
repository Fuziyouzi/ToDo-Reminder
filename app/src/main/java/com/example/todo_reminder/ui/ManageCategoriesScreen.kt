package com.example.todo_reminder.ui

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todo_reminder.R
import javax.sql.RowSetWriter

@Composable
fun ManageCategoriesScreen(modifier: Modifier = Modifier, backButton: () -> Unit) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        TopBar(text = "Manage Categories", onClick = backButton)
        LazyColumn(
            modifier = modifier.padding(horizontal = 6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(listOfCategory, key = { it.category }) {
                CategoryItem(text = it.category, onClick = { })
            }
        }
        CreateNew(onClick = { })


    }
}

@Composable
fun CategoryItem(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) EditDialog(showDialog = { showDialog = it }, text = "")

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = modifier.size(26.dp),
            painter = painterResource(id = R.drawable.emoji),
            contentDescription = "Category Icon"
        )
        Text(
            modifier = modifier
                .weight(1f)
                .padding(start = 16.dp), text = text
        )
        IconButton(onClick = { expanded = true }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More Category Item")
            DropdownMenu(
                expanded = expanded,
                modifier = modifier
                    .background(MaterialTheme.colors.surface, RoundedCornerShape(84)),
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(onClick = { showDialog = true }) {
                    Text(
                        text = "Edit", color = MaterialTheme.colors.onPrimary,
                        fontSize = 14.sp
                    )
                }
                DropdownMenuItem(onClick = { showDialog = true }) {
                    Text(
                        text = "Hide", color = MaterialTheme.colors.onPrimary,
                        fontSize = 14.sp
                    )
                }
                DropdownMenuItem(onClick = { showDialog = true }) {
                    Text(
                        text = "Delete", color = MaterialTheme.colors.onPrimary,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun CreateNew(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier.padding(start = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add New Category",
            tint = MaterialTheme.colors.secondary
        )
        TextButton(modifier = modifier.padding(start = 6.dp), onClick = onClick) {
            Text(text = "Create New", color = MaterialTheme.colors.secondary, fontSize = 18.sp)
        }
    }
}

@Composable
fun EditDialog(modifier: Modifier = Modifier, showDialog: (Boolean) -> Unit, text: String) {
    Dialog(onDismissRequest = { showDialog(false) }) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(14.dp),
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Column(modifier = modifier.padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Edit Category",
                    fontSize = 22.sp,
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
    }

}