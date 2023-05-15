package com.example.todo_reminder.ui

data class Category(var image: Int, var selected: Boolean, var category: String)

val listOfCategory = mutableListOf<Category>(
    Category(2,true, "All" ),
    Category(2, false, "Personal"),
    Category(2, false,"Work"),
    Category(2, false,"Wishlist"),
    Category(2, false,"Birthday")
)




