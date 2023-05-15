package com.example.todo_reminder.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_reminder.R
import com.example.todo_reminder.ui.theme.Grey
import com.example.todo_reminder.ui.theme.LightGrey
import com.example.todo_reminder.ui.theme.SecondaryGrey
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {

    var textField by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(6.dp),
    ) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        keyboardController?.show()
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = modifier
                    .focusRequester(focusRequester),
                value = textField,
                onValueChange = { textField = it },
                placeholder = {
                    Text(text = "Search", color = MaterialTheme.colors.onSecondary)
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = MaterialTheme.colors.secondary,
                    textColor = MaterialTheme.colors.onPrimary,
                    backgroundColor = MaterialTheme.colors.primaryVariant
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        focusRequester.freeFocus()
                        keyboardController?.hide()
                    }
                ),
                singleLine = true,
                maxLines = 1,
                trailingIcon = {
                    if (textField != "") {
                        Icon(
                            modifier = modifier.clickable { textField = "" },
                            imageVector = Icons.Default.Close, contentDescription = "",
                            tint = MaterialTheme.colors.onSecondary
                        )
                    }
                }
            )

            Spacer(modifier = modifier.weight(1f))
            Surface(
                modifier = modifier
                    .width(1.dp)
                    .height(10.dp), color = MaterialTheme.colors.onSecondary
            ) {
            }
            TextButton(
                modifier = modifier,
                onClick = onClick
            ) {
                Text(text = "Cancel", color = MaterialTheme.colors.secondary, fontSize = 14.sp)
            }
        }
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .height(0.5.dp),
            color = MaterialTheme.colors.onSecondary
        ) {

        }
        EmptySearchScreen()
    }
}

@Composable
fun EmptySearchScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier.size(350.dp),
            painter = painterResource(id = R.drawable.no_result_search),
            contentDescription = "No result search",
            colorFilter = if (isSystemInDarkTheme()) ColorFilter.lighting(
                LightGrey, Grey
            ) else ColorFilter.lighting(
                SecondaryGrey,
                LightGrey
            )
        )
        Text(
            text = "No results",
            color = MaterialTheme.colors.onSecondary,
            fontSize = 20.sp
        )
    }
}

@Composable
fun SearchList(modifier: Modifier = Modifier) {

}