package com.kc.todoapp.data.repository

import com.google.gson.Gson
import com.kc.todoapp.data.source.api.TodoApi
import com.kc.todoapp.domain.model.DTodo
import com.kc.todoapp.domain.model.DTodoResponse
import com.kc.todoapp.domain.model.mapToDomain
import com.kc.todoapp.domain.repository.TodoRepository
import io.reactivex.Observable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class TodoRepositoryImpl(val todoApi: TodoApi) : TodoRepository {
    override fun getTodos(): Observable<DTodoResponse> {
        return todoApi.getTodoList().map {
            it.mapToDomain()
        }
    }

    override fun updateTodo(todo: DTodo, id: Int): Observable<DTodo> {
        val json = Gson().toJson(todo)
        val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        return todoApi.updateTodo(body, id).map {
            it.mapToDomain()
        }
    }

    override fun addTodo(todo: DTodo): Observable<DTodo> {
        val json = Gson().toJson(todo)
        val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        return todoApi.createTodo(body).map {
            it.mapToDomain()
        }
    }

    override fun deleteTodo(id: Int): Observable<DTodo> {
        return todoApi.deleteTodo(id).map {
            it.mapToDomain()
        }
    }

}