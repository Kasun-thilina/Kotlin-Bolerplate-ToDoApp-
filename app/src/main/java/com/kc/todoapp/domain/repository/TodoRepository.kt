package com.kc.todoapp.domain.repository

import com.kc.todoapp.domain.model.DTodo
import com.kc.todoapp.domain.model.DTodoResponse
import io.reactivex.Observable

interface TodoRepository {
    fun getTodos(): Observable<DTodoResponse>
    fun updateTodo(todo: DTodo, id: Int): Observable<DTodo>
    fun addTodo(todo: DTodo): Observable<DTodo>
    fun deleteTodo(id: Int): Observable<DTodo>
}