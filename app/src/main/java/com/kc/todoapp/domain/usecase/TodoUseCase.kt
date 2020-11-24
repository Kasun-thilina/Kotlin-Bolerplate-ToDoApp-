package com.kc.todoapp.domain.usecase

import com.kc.todoapp.domain.model.DTodo
import com.kc.todoapp.domain.repository.TodoRepository

class TodoUseCase(
    val todoRepository: TodoRepository
) {
    fun getTodoList() = todoRepository.getTodos()
    fun updateTodo(todo: DTodo, id: Int) = todoRepository.updateTodo(todo, id)
    fun addTodo(todo: DTodo) = todoRepository.addTodo(todo)
    fun deleteTodo(id: Int) = todoRepository.deleteTodo(id)
}