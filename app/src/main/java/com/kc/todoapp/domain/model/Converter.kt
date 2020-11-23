package com.kc.todoapp.domain.model

import com.kc.todoapp.data.source.model.Todo
import com.kc.todoapp.data.source.model.TodoListResponse

fun TodoListResponse.mapToDomain(): DTodoResponse = DTodoResponse(
    todoList = todoList.map { todo ->
        todo.mapToDomain()
    }
)

fun Todo.mapToDomain(): DTodo = DTodo(
    userId = userId,
    id = id,
    title = title,
    completed = completed
)