package com.example.todo_reminder.ui

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.todo_reminder.R
import javax.sql.RowSetWriter

@Composable
fun ManageCategoriesScreen(modifier: Modifier = Modifier, backButton: () -> Unit) {

    val scrollState = rememberScrollState()
    val list = listOfCategory


    Column(
        modifier = modifier
            .padding(6.dp)
            .fillMaxSize()
            .scrollable(scrollState, orientation = Orientation.Vertical)

    ) {
        TopBar(text = "Manage Categories", onClick = backButton)
        LazyColumn(
            modifier = modifier.padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(list, key = { it.category }) { itList ->
                CategoryItem(
                    text = itList.category, deleteClick = {
                        val index = list.indexOf(itList)
                        list.removeAt(index)
                    }
                )
            }
        }
        CreateNew(createNew = { list.add(it) })

    }
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    text: String,
    deleteClick: () -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var visibility by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) }
    var emoji by remember { mutableStateOf(R.drawable.emoji) }
    var category by remember { mutableStateOf("") }

    if (showDialog) {
        EditDialog(
            showDialog = { showDialog = it },
            emojiEdit = { emoji = it },
            category = { category = it },
            dialogText = "Edit Category",
            emoji = emoji
        )
    }


    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = modifier.size(26.dp),
            painter = if(!showDialog) painterResource(id = emoji) else painterResource(id = R.drawable.emoji),
            contentDescription = "Category Icon"
        )
        Text(
            modifier = modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = if (category != "") category else text
        )
        IconButton(onClick = { visibility = !visibility }) {
            Icon(
                modifier = modifier.size(20.dp),
                painter = painterResource(if (visibility) R.drawable.visible_eye else R.drawable.not_visible_eye),
                contentDescription = "Eye visible",
                tint = MaterialTheme.colors.secondary
            )
        }
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
                DropdownMenuItem(
                    onClick = deleteClick
                ) {
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
fun CreateNew(modifier: Modifier = Modifier, createNew: (Category) -> Unit) {

    var showDialog by remember { mutableStateOf(false) }
    var emoji by remember { mutableStateOf(R.drawable.emoji) }
    var category by remember { mutableStateOf("") }

    if (showDialog) {
        EditDialog(
            showDialog = { showDialog = it },
            emojiEdit = { emoji = it },
            category = { category = it },
            dialogText = "Create New Category",
            emoji = emoji
        )
    }

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
        TextButton(modifier = modifier.padding(start = 6.dp), onClick = {
            showDialog = true
        }) {
            Text(
                text = "Create New",
                color = MaterialTheme.colors.secondary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun EditDialog(
    modifier: Modifier = Modifier,
    showDialog: (Boolean) -> Unit,
    emojiEdit: (Int) -> Unit,
    category: (String) -> Unit,
    emoji: Int,
    dialogText: String
) {

    var text by remember { mutableStateOf("") }
    var emojiDialog by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = { showDialog(false) }) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(14.dp),
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Column(
                modifier = modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = modifier,
                    text = dialogText,
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onPrimary,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    modifier = modifier
                        .padding(top = 24.dp, bottom = 8.dp)
                        .fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "Edit category here",
                            color = MaterialTheme.colors.onSecondary
                        )
                    },
                    value = text,
                    onValueChange = { text = it },
                    shape = MaterialTheme.shapes.medium.copy(CornerSize(24)),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        cursorColor = MaterialTheme.colors.secondary,
                        textColor = MaterialTheme.colors.onPrimary
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { })
                )
                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Category Icon",
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onPrimary
                    )
                    Spacer(modifier = modifier.weight(1f))
                    IconButton(onClick = { emojiDialog = true }) {
                        Image(
                            modifier = modifier.size(25.dp),
                            painter = painterResource(id = emoji),
                            contentDescription = "Change emoji image"
                        )
                    }
                }
                Row(
                    modifier = modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = { showDialog(false) },
                    ) {
                        Text(
                            text = "CANCEL",
                            fontSize = 17.sp,
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    TextButton(
                        onClick = {
                            emojiEdit(emoji)
                            category(text)
                            showDialog(false)
                        },
                    ) {
                        Text(
                            text = "SAVE",
                            fontSize = 17.sp,
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }
        }
        if (emojiDialog) EmojiEditDialog(
            emojiDialog = { emojiDialog = it },
            emoji = { emojiEdit(it) })
    }
}

@Composable
fun EmojiEditDialog(
    modifier: Modifier = Modifier,
    emojiDialog: (Boolean) -> Unit,
    emoji: (Int) -> Unit
) {
    Popup(
        alignment = Alignment.CenterEnd,
        onDismissRequest = { emojiDialog(false) },
        properties = PopupProperties()
    ) {
        Card(
            modifier = modifier
                .padding(end = 60.dp)
                .size(160.dp),
            shape = RoundedCornerShape(16.dp),
            backgroundColor = MaterialTheme.colors.primary
        ) {
            LazyVerticalGrid(
                modifier = modifier.padding(15.dp),
                columns = GridCells.Fixed(4),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(listOfEmoji, key = { it.id }) {
                    IconButton(onClick = {
                        emoji(it.image)
                        emojiDialog(false)
                    }) {
                        Image(
                            modifier = modifier.size(26.dp),
                            painter = painterResource(id = it.image),
                            contentDescription = "Emoji"
                        )
                    }
                }

            }
        }
    }

}
