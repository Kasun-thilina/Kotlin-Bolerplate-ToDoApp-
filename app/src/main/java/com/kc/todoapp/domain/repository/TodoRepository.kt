package com.kc.todoapp.domain.repository

import com.kc.todoapp.data.source.model.Todo
import com.kc.todoapp.domain.model.DTodo
import com.kc.todoapp.domain.model.DTodoResponse
import io.reactivex.Observable

interface TodoRepository {
    fun getTodos(): Observable<DTodoResponse>
    fun updateTodo(todo: Todo, id:Int): Observable<DTodo>
    fun addTodo(todo:Todo): Observable<DTodo>
    fun deleteTodo(id:Int): Observable<DTodo>
}