package com.example.todo_reminder.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_reminder.ui.theme.*

@Composable
fun ToDoItem(
    modifier: Modifier = Modifier,
    checkBox: () -> Boolean,
    text: String,
    onCheckedChange: (Boolean) -> Unit,
    toDoScreen: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium.copy(CornerSize(24)))
            .clickable(onClick = toDoScreen)
            .background(if (!checkBox.invoke()) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.onBackground),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            modifier = modifier,
            checked = checkBox.invoke(),
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.secondary,
                uncheckedColor = MaterialTheme.colors.onSurface,
                checkmarkColor = MaterialTheme.colors.onPrimary
            )
        )
        Text(
            modifier = modifier.padding(start = 14.dp),
            text = text,
            fontSize = 17.sp,
            maxLines = 2,
            color = if (!checkBox.invoke()) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
fun ToDoGridItem(
    modifier: Modifier = Modifier,
    checkBox: Boolean,
    text: String,
    onCheckedChange: (Boolean) -> Unit
) {

    Box(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.medium.copy(CornerSize(24)))
            .background(if (!checkBox) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.onBackground)
            .clickable { },
        contentAlignment = Alignment.TopStart
    ) {
        Checkbox(
            modifier = modifier,
            checked = checkBox,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.secondary,
                uncheckedColor = MaterialTheme.colors.onSurface,
                checkmarkColor = MaterialTheme.colors.onPrimary
            )
        )
        Text(
            modifier = modifier.padding(
                start = 48.dp,
                top = 10.dp,
                bottom = 10.dp,
                end = 10.dp
            ),
            text = text,
            fontSize = 17.sp,
            maxLines = 8,
            color = if (!checkBox) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSecondary
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewToDo() {
    ToDoReminderTheme() {
    }

}