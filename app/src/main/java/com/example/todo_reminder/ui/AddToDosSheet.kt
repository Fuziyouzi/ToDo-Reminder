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
    focusRequester: FocusRequester
) {

    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

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
                .focusRequester(focusRequester),
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
            keyboardActions = KeyboardActions(onDone = { focusRequester.freeFocus() })
        )
        LazyColumn() {

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
                    text = "Category",
                    color = colors.onSecondary
                )
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
                    .clickable { },
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
    DropdownMenu(
        expanded = expanded,
        modifier = modifier
            .size(height = 180.dp, width = 130.dp)
            .background(colors.surface, RoundedCornerShape(84)),
        offset = DpOffset(20.dp, (-210).dp),
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(onClick = { /*TODO*/ }) {
            Text(
                text = "No Category", color = colors.secondary,
                fontSize = 15.sp
            )
        }
        listOfCategory.forEach {
            DropdownMenuItem(onClick = { /*TODO*/ }) {
                Text(
                    text = it.category,
                    color = colors.onPrimary,
                    fontSize = 15.sp
                )
            }
        }
    }

}


@Composable
fun SubToDosAdd(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        RadioButton(
            selected = true,
            onClick = { /*TODO*/ },
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.secondary
            )
        )
        TextField(
            value = "Add SubTask",
            modifier = modifier.weight(1f),
            onValueChange = {},
            colors = TextFieldDefaults.textFieldColors(
                textColor = colors.onPrimary,
                backgroundColor = colors.primaryVariant
            )
        )
        Icon(
            imageVector = Icons.Default.Close, contentDescription = "",
            modifier = modifier.clickable { })
    }
}


@Preview
@Composable
fun PreviewSheetAdd() {
    ToDoReminderTheme {
        SubToDosAdd()
    }
}