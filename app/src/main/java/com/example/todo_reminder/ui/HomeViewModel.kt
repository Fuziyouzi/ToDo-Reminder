package com.example.todo_reminder.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor () : ViewModel() {

    private val gridView = MutableStateFlow(false)
    val _gridView = gridView.asStateFlow()


    fun changeToDoView() {
        gridView.value = !gridView.value
    }




}
