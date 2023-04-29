package com.example.todo_reminder.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = LightBlack,
    primaryVariant = LightBlack,
    secondary = Blue,
    background = LightBlack,
    surface = Grey,
    onPrimary = White,
    onSecondary = LightGrey,
    onBackground = Grey,
    onSurface = Grey

)

private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = SecondaryWhite,
    secondary = LightBlue,
    background = White,
    onPrimary = Black,
    surface = SecondaryGrey,
    onBackground = LightBlue,
    onSecondary = SecondaryGrey,
    onSurface = SecondaryGrey

    /* Other default colors to override

    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ToDoReminderTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = colors.background
        )
    }



    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}