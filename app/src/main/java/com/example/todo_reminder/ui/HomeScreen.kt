package com.example.todo_reminder.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.todo_reminder.R
import com.example.todo_reminder.listOfCategory
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
    var toDoView by remember { mutableStateOf(false)}

    Scaffold(
        modifier = modifier.padding(6.dp),
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
                            navController.navigate(Screen.ToDoScreen.route) {
                                launchSingleTop = true
                            }
                        },
                    contentDescription = "Search main screen",
                )
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    modifier = modifier
                        .size(34.dp)
                        .clickable { expanded = true },
                    contentDescription = "More main screen"
                )
            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                listOfCategory.forEach {
                    item {
                        CategoryItem(
                            onClick = { },
                            text = it.category
                        )
                    }
                }

            }
            if (!toDoView) TodoList() else ToDoGrid()
        }
        DropdownMenu(
            expanded = expanded,
            modifier = modifier
                .background(colors.surface, RoundedCornerShape(84)),
            offset = DpOffset(220.dp, (-210).dp),
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = { /*TODO*/ }) {
                Text(
                    text = "Manage categories", color = colors.onPrimary,
                    fontSize = 14.sp
                )
            }
            DropdownMenuItem(onClick = { toDoView = !toDoView }) {
                Text(
                    text = "Grid View", color = colors.onPrimary,
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


@Composable
fun CategoryItem(modifier: Modifier = Modifier, onClick: () -> Unit, text: String) {

    Card(
        modifier = modifier.clickable { onClick },
        shape = MaterialTheme.shapes.medium.copy(CornerSize(24)),
        backgroundColor = colors.onBackground,
        elevation = 12.dp
    ) {
        Row(
            modifier = modifier.padding(vertical = 14.dp, horizontal = 16.dp),
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

@Composable
fun TodoList(modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        list.forEach {
            item {
                ToDoItem(checkBox = it.check, text = it.text, onCheckedChange = { })
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ToDoGrid(modifier: Modifier = Modifier){

    LazyVerticalStaggeredGrid(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 6.dp
    ) {
        list.forEach {
            item {
                ToDoGridItem(checkBox = it.check, text = it.text, onCheckedChange = { })
            }
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

