package com.example.todo_reminder.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.todo_reminder.R
import com.example.todo_reminder.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    val scaffoldState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val keyBoard = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    ModalBottomSheetLayout(
        modifier = modifier.fillMaxSize(),
        sheetState = scaffoldState,
        sheetShape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
        sheetBackgroundColor = colors.primaryVariant,
        sheetContent = {
            if (!scaffoldState.isVisible) keyBoard?.hide()
            AddTodosSheet(modifier, focusRequester)
        }
    ) {
        HomeScreen(
            navController = navController,
            scaffoldState = scaffoldState,
            focusRequester = focusRequester,
            keyboard = keyBoard
        )
    }
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    focusRequester: FocusRequester,
    scaffoldState: ModalBottomSheetState,
    keyboard: SoftwareKeyboardController?
) {

    var expanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var toDoView by remember { mutableStateOf(false) }
    var textMenuView by remember { mutableStateOf("Grid View") }
    if (!toDoView) {
        expanded = false
        textMenuView = "Grid View"
    } else {
        expanded = false
        textMenuView = "List View"
    }
    var choosenCategory by remember { mutableStateOf(true) }

    Scaffold(
        modifier = modifier.padding(8.dp),
        floatingActionButton = {
            FloatingButton(
                {
                    scope.launch {
                        scaffoldState.show()
                        focusRequester.requestFocus()
                        keyboard?.show()
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    modifier = modifier
                        .padding(end = 10.dp)
                        .size(34.dp)
                        .clickable {
                            navController.navigate(Screen.SearchScreen.route) {
                                launchSingleTop = true
                            }
                        },
                    contentDescription = "Search main screen",
                )
                Box(modifier = modifier) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        modifier = modifier
                            .size(34.dp)
                            .clickable { expanded = true },
                        contentDescription = "More main screen"
                    )
                    DropdownMenu(
                        expanded = expanded,
                        modifier = modifier
                            .background(colors.surface, RoundedCornerShape(84)),
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(onClick = { navController.navigate(Screen.ManageCategories.route) }) {
                            Text(
                                text = "Manage categories", color = colors.onPrimary,
                                fontSize = 14.sp
                            )
                        }
                        DropdownMenuItem(onClick = { toDoView = !toDoView }) {
                            Text(
                                text = textMenuView, color = colors.onPrimary,
                                fontSize = 14.sp
                            )
                        }
                        DropdownMenuItem(
                            onClick = { navController.navigate(Screen.SettingsScreen.route) }
                        ) {
                            Text(
                                text = "Settings", color = colors.onPrimary,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                listOfCategory.forEach {
                    item {
                        RadioButtonCategory(
                            onClick = { !choosenCategory },
                            selected = if (it.selected) choosenCategory else false,
                            text = it.category
                        )
                    }
                }

            }
            if (!toDoView) TodoList() else ToDoGrid()
        }
    }
}

@Composable
fun FloatingButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium.copy(CornerSize(24)),
        contentColor = White
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = "Localized description",
            modifier = modifier.size(27.dp)
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RadioButtonCategory(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    selected: Boolean,
    text: String
) {

    Card(
        modifier = modifier,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium.copy(CornerSize(24)),
        backgroundColor = if (!selected) colors.background else colors.secondary,
        elevation = 12.dp
    ) {
        Row(
            modifier = modifier
                .padding(vertical = 14.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = modifier.size(24.dp),
                painter = painterResource(id = R.drawable.emoji),
                contentDescription = "fsa"
            )
            Text(
                text = text,
                modifier = modifier.padding(start = 16.dp),
                color = colors.onPrimary
            )
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoList(modifier: Modifier = Modifier) {

    val checkedListState by remember { mutableStateOf(list.filter { it.check }) }
    val uncheckedListState by remember { mutableStateOf(list.filter { !it.check }) }


    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(uncheckedListState, key = { it.number }) {

            ToDoItem(checkBox = it.check, text = it.text, onCheckedChange = { })
        }
        stickyHeader {
            Row(
                modifier = modifier
                    .padding(vertical = 10.dp, horizontal = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier,
                    text = "Completed", fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Surface(
                    modifier = modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth()
                        .height(0.5.dp),
                    color = colors.onSecondary
                ) {}
            }
        }

        items(checkedListState, key = { it.number }) {
            ToDoItem(checkBox = it.check, text = it.text, onCheckedChange = {})
        }
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ToDoGrid(modifier: Modifier = Modifier) {

    val checkedListState by remember { mutableStateOf(list.filter { it.check }) }
    val uncheckedListState by remember { mutableStateOf(list.filter { !it.check }) }
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 8.dp
    ) {
        items(uncheckedListState, key = { it.number }) {

            ToDoGridItem(checkBox = it.check, text = it.text, onCheckedChange = { })
        }

        items(checkedListState, key = { it.number }) {
            ToDoGridItem(checkBox = it.check, text = it.text, onCheckedChange = {})
        }
    }
}

@Composable
fun EmptyMainScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .padding(top = 160.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.todolist),
            contentDescription = "Todo list",
            colorFilter = if (isSystemInDarkTheme()) ColorFilter.lighting(
                LightGrey, Grey
            ) else ColorFilter.lighting(
                SecondaryGrey,
                LightGrey
            )
        )
        Text(
            text = "No To-Dos",
            modifier = modifier.padding(top = 12.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = LightGrey
        )
    }
}

fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}

