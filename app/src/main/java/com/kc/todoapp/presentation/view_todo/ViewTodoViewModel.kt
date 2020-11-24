package com.kc.todoapp.presentation.view_todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kc.todoapp.domain.model.DTodo
import com.kc.todoapp.domain.usecase.TodoUseCase
import com.kc.todoapp.util.extensions.setError
import com.kc.todoapp.util.extensions.setLoading
import com.kc.todoapp.util.extensions.setSuccess
import com.kc.todoapp.util.network.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ViewTodoViewModel(
    private val todoUseCase: TodoUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val addTodoLiveData = MutableLiveData<Resource<DTodo>>()

    fun addTodo(todo: DTodo) {
        addTodoLiveData.setLoading()
        compositeDisposable.add(
            todoUseCase.addTodo(todo)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    addTodoLiveData.setSuccess(it, null)
                }, {
                    addTodoLiveData.setError(it.message)
                }
                )
        )
    }

    fun updateTodo(todo: DTodo) {
        addTodoLiveData.setLoading()
        compositeDisposable.add(
            todoUseCase.updateTodo(todo, todo.id!!)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    addTodoLiveData.setSuccess(it, null)
                }, {
                    addTodoLiveData.setError(it.message)
                }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}