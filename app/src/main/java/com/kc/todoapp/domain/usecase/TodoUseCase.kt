package com.kc.todoapp.domain.usecase

import com.kc.todoapp.data.source.model.Todo
import com.kc.todoapp.domain.model.DTodoResponse
import com.kc.todoapp.domain.repository.TodoRepository
import java.util.*

class TodoUseCase(
    val todoRepository: TodoRepository
) {
    fun getTodoList() = todoRepository.getTodos()
    fun updateTodo(todo:Todo,id:Int) = todoRepository.updateTodo(todo, id)
    fun addTodo(todo: Todo) = todoRepository.addTodo(todo)
    fun deleteTodo(id: Int)=todoRepository.deleteTodo(id)
}