package com.example.my_task_manager



data class Task(
    val title: String,
    val description: String,
    val date: String,
    val dateRange: Boolean=false,
    var isCompleted: Boolean=false


)




