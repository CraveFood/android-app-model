package com.cravefood.androidappmodel.di

import com.cravefood.androidappmodel.MyApp
import com.cravefood.androidappmodel.data.repository.TodoRepository
import com.cravefood.androidappmodel.data.repository.TodoRepositoryImpl
import com.cravefood.androidappmodel.data.repository.repository_util.RetrofitBuilder
import com.cravefood.androidappmodel.ui.TodoListViewModel
import com.cravefood.androidappmodel.ui.TodoViewModel
import com.cravefood.androidappmodel.ui.TodosViewModel
import com.cravefood.androidappmodel.util.NetworkUtils
import com.cravefood.androidappmodel.util.SharedPreferencesUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val ViewModelModules: Module = module {
    viewModel { TodosViewModel(get()) }
    viewModel { TodoViewModel(get()) }
    viewModel { TodoListViewModel(get()) }
}


val RepositoryModules: Module = module {
    single { RetrofitBuilder(MyApp.appContext.cacheDir, get()) }
    single { TodoRepositoryImpl(get()) as TodoRepository }
}

val CommonModules: Module = module {
    single { SharedPreferencesUtils() }
    single { NetworkUtils(androidContext(), get(), "https://jsonplaceholder.typicode.com/") }
}