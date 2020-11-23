package com.kc.todoapp.domain.model

import com.kc.todoapp.data.source.model.Todo

data class DTodoResponse(
    val todoList: List<DTodo>?
)