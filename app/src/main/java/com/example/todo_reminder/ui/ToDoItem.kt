package com.example.todo_reminder.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_reminder.ui.theme.*

@Composable
fun ToDoItem(
    modifier: Modifier = Modifier,
    checkBox: Boolean,
    text: String,
    onCheckedChange: (Boolean) -> Unit
) {

    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium.copy(CornerSize(24)),
        color =
        if (!checkBox) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.onBackground
    ) {

        Row(
            modifier = modifier
                .clickable { }
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkBox,
                modifier = modifier,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colors.secondary,
                    uncheckedColor = MaterialTheme.colors.onSurface
                )
            )
            Text(
                text = text,
                fontSize = 17.sp,
                modifier = modifier.padding(start = 14.dp),
                maxLines = 2,
                color = if (!checkBox) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSecondary
            )
        }
    }
}

@Composable
fun ToDoGridItem(
    modifier: Modifier = Modifier,
    checkBox: Boolean,
    text: String,
    onCheckedChange: (Boolean) -> Unit
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium.copy(CornerSize(24)),
        color = if (!checkBox) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.onBackground
    ) {
        Box(
            modifier = modifier.clickable { },
            contentAlignment = Alignment.TopStart
        ) {
            Checkbox(
                checked = checkBox,
                modifier = modifier,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colors.secondary,
                    uncheckedColor = MaterialTheme.colors.onSurface
                )
            )
            Text(
                text = text,
                fontSize = 17.sp,
                modifier = modifier.padding(start = 48.dp, top = 10.dp),
                maxLines = 8,
                color = if (!checkBox) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSecondary
            )
        }
    }

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewToDo() {
    ToDoReminderTheme() {
        ToDoItem(checkBox = false, text = "Todo Something", onCheckedChange = { })
    }

}