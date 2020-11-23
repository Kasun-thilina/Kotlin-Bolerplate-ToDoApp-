package com.kc.todoapp.data.source.api

import com.kc.todoapp.data.source.model.Todo
import com.kc.todoapp.data.source.model.TodoListResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface TodoApi {
    @GET("todos")
    fun getTodoList(
    ): Observable<TodoListResponse>

    @PUT("todos/{id}")
    fun updateTodo(
        @Body body:RequestBody,@Path("id")id:Int):Observable<Todo>

    @POST("todos/")
    fun createTodo(
        @Body body:RequestBody):Observable<Todo>

    @DELETE("todos/{id}")
    fun deleteTodo(@Path("id")id:Int):Observable<Todo>
}