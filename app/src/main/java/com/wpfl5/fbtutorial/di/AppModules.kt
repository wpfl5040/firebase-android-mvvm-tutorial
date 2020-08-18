package com.wpfl5.fbtutorial.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wpfl5.fbtutorial.repository.StoreRepository
import com.wpfl5.fbtutorial.repository.StoreRepositoryImpl
import com.wpfl5.fbtutorial.ui.main.MainViewModel
import com.wpfl5.fbtutorial.ui.main.firestore.FireStoreViewModel
import com.wpfl5.fbtutorial.usecase.StoreUseCase
import com.wpfl5.fbtutorial.usecase.StoreUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.experimental.builder.factory

val appModule: List<Module>
    get() = listOf(viewModelModule, repoModule, useCaseModule)

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { FireStoreViewModel(get()) }
}

val repoModule = module {
    single { Firebase.firestore }
    factory<StoreRepositoryImpl>() bind StoreRepository::class

}

val useCaseModule = module {
    factory<StoreUseCaseImpl>() bind StoreUseCase::class
}