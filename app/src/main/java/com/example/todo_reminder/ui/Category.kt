package com.example.todo_reminder.ui

data class Category(val image: Int, val selected: Boolean, val category: String)

val listOfCategory = listOf(
    Category(2,true, "All" ),
    Category(2, false, "Personal"),
    Category(2, false,"Work"),
    Category(2, false,"Wishlist"),
    Category(2, false,"Birthday")
)
