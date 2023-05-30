package com.example.todo_reminder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_reminder.R
import com.example.todo_reminder.ui.theme.Shapes
import com.example.todo_reminder.ui.theme.ToDoReminderTheme

@Composable
fun AddTodosSheet(
    modifier: Modifier = Modifier,
    focusRequester:() -> FocusRequester
) {

    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    var categoryText by remember { mutableStateOf("Category") }
    var subTasks by remember { mutableStateOf(false) }
    var count by remember { mutableStateOf(0) }
    subTasks = count != 0


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = modifier
                .padding(top = 15.dp, bottom = 30.dp)
                .size(width = 32.dp, height = 3.dp),
            backgroundColor = colors.onSecondary,
            shape = MaterialTheme.shapes.medium.copy(CornerSize(24))
        ) {

        }
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .focusRequester(focusRequester.invoke()),
            placeholder = {
                Text(
                    text = "Input new task here",
                    color = colors.onSecondary
                )
            },
            value = text,
            onValueChange = { text = it },
            shape = MaterialTheme.shapes.medium.copy(CornerSize(24)),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colors.surface,
                cursorColor = colors.secondary,
                textColor = colors.onPrimary
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusRequester.invoke().freeFocus() })
        )
        if (subTasks) {
            SubToDosList(modifier = modifier, count = count, remove = { count -= 1})
        }
        Row(
            modifier = modifier
                .padding(vertical = 18.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = modifier.clickable {
                    expanded = true
                },
                color = colors.surface,
                shape = Shapes.medium.copy(CornerSize(24))
            ) {
                Text(
                    modifier = modifier.padding(8.dp),
                    text = categoryText,
                    color = colors.onSecondary
                )
                DropdownMenu(
                    expanded = expanded,
                    modifier = modifier
                        .size(height = 180.dp, width = 130.dp)
                        .background(colors.surface, RoundedCornerShape(84)),
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(onClick = { categoryText = "No Category" }) {
                        Text(
                            text = "No Category", color = colors.secondary,
                            fontSize = 15.sp
                        )
                    }
                    listOfCategory.forEach {
                        DropdownMenuItem(onClick = {
                            categoryText = it.category
                            expanded = false
                        }) {
                            Text(
                                text = it.category,
                                color = colors.onPrimary,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
            Icon(
                modifier = modifier
                    .size(26.dp)
                    .clickable { },
                painter = painterResource(id = R.drawable.baseline_notifications_24),
                contentDescription = ""
            )
            Icon(
                modifier = modifier
                    .size(26.dp)
                    .clickable {
                        count++
                    },
                imageVector = Icons.Default.Add, contentDescription = ""
            )
            Spacer(modifier = modifier.weight(1f))
            Button(
                onClick = { /*TODO*/ },
                modifier = modifier,
                shape = Shapes.medium.copy(CornerSize(24)),
                colors = ButtonDefaults.buttonColors(
                    colors.secondary,
                    contentColor = colors.onPrimary
                )
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
                Text(text = "Add", fontSize = 16.sp, modifier = modifier.padding(start = 4.dp))
            }
        }
    }
}

@Composable
fun SubToDosList(modifier: Modifier = Modifier, count: Int, remove: () -> Unit) {
    LazyColumn(modifier = modifier) {
        repeat(count) {
            item {
                SubToDosAdd(modifier = modifier, remove = remove)
            }
        }

    }
}


@Composable
fun SubToDosAdd(modifier: Modifier = Modifier, remove: () -> Unit) {
    var completed by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        RadioButton(
            selected = completed,
            onClick = { completed = !completed },
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.secondary
            )
        )
        TextField(
            value = text,
            modifier = modifier.weight(1f),
            placeholder = {
                Text(text = "Add SubTask", color = colors.onSecondary)
            },
            onValueChange = { text = it },
            colors = TextFieldDefaults.textFieldColors(
                textColor = if (!completed) colors.onPrimary else colors.onSecondary,
                backgroundColor = colors.primaryVariant,
                cursorColor = colors.secondary
            )
        )
        IconButton(onClick = remove) {
            Icon(
                imageVector = Icons.Default.Close, contentDescription = "Delete SubTask"
            )
        }

    }
}


@Preview
@Composable
fun PreviewSheetAdd() {
    ToDoReminderTheme {
    }
}