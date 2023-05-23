package com.example.todo_reminder.ui

import androidx.annotation.DrawableRes
import com.example.todo_reminder.R

data class Category(var image: Int, var selected: Boolean, var category: String)
data class Emoji(var id: Int, @DrawableRes var image: Int)

val listOfCategory = mutableListOf<Category>(
    Category(2, true, "All"),
    Category(2, false, "Personal"),
    Category(2, false, "Work"),
    Category(2, false, "Wishlist"),
    Category(2, false, "Birthday")
)

val listOfEmoji = mutableListOf<Emoji>(
    Emoji(0, R.drawable.emoji),
    Emoji(1, R.drawable.emoji1),
    Emoji(2, R.drawable.emoji2),
    Emoji(3, R.drawable.emoji3),
    Emoji(4, R.drawable.emoji4),
    Emoji(5, R.drawable.emoji5),
    Emoji(6, R.drawable.emoji6),
    Emoji(7, R.drawable.emoji7),
    Emoji(8, R.drawable.emoji8),
    Emoji(9, R.drawable.emoji9),
    Emoji(10, R.drawable.emoji10),
    Emoji(11, R.drawable.emoji11),
    )



