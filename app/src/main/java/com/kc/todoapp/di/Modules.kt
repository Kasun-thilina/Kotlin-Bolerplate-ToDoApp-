package com.kc.todoapp.di

import com.kc.todoapp.data.repository.TodoRepositoryImpl
import com.kc.todoapp.domain.repository.TodoRepository
import com.kc.todoapp.domain.usecase.TodoUseCase
import com.kc.todoapp.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectFeature() = loadFeature


private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            viewModelModule,
            useCaseModule,
            repositoryModule
        )
    )
}

val viewModelModule: Module = module {
    viewModel { HomeViewModel(todoUseCase = get()) }
}

val useCaseModule: Module = module {
    factory { TodoUseCase(todoRepository = get()) }
}

val repositoryModule: Module = module {
    single<TodoRepository> {
        TodoRepositoryImpl(
            todoApi = get()
        )
    }

}