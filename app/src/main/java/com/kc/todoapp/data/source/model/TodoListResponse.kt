package com.kc.todoapp.data.source.model

import com.google.gson.annotations.SerializedName

data class TodoListResponse (
    val todoList:List<Todo>
)