package com.kc.todoapp.data.source.api

import com.kc.todoapp.data.source.model.TodoResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoApi {
    @GET("todos")
    fun getTodoList(
    ): Observable<TodoResponse>


}