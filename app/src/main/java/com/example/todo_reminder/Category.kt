package com.example.todo_reminder

data class Category(val image: Int, val category: String)

val listOfCategory = listOf(
    Category(2, "All"),
    Category(2, "Personal"),
    Category(2, "Work"),
    Category(2, "Wishlist"),
    Category(2, "Birthday")
)
