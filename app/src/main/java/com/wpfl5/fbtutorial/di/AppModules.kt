package com.wpfl5.fbtutorial.di

import com.wpfl5.fbtutorial.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: List<Module>
    get() = listOf<Module>(viewModelModule, repoModule )

val viewModelModule = module {
    viewModel { MainViewModel() }
}

val repoModule = module {

}