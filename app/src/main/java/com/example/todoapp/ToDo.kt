package com.example.todoapp

data class ToDo (
    val item: String,
    var isChecked: Boolean = false
)