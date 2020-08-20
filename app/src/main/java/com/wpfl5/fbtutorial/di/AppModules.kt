package com.wpfl5.fbtutorial.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.wpfl5.fbtutorial.repository.*
import com.wpfl5.fbtutorial.ui.main.MainViewModel
import com.wpfl5.fbtutorial.ui.main.auth.AuthViewModel
import com.wpfl5.fbtutorial.ui.main.crashlytics.CrashlyticsViewModel
import com.wpfl5.fbtutorial.ui.main.firestore.FireStoreViewModel
import com.wpfl5.fbtutorial.ui.main.storage.StorageViewModel
import com.wpfl5.fbtutorial.usecase.*
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
    viewModel { AuthViewModel(get()) }
    viewModel { CrashlyticsViewModel(get()) }
    viewModel { StorageViewModel(get()) }
}

val repoModule = module {
    single { Firebase.firestore }
    single { Firebase.auth }
    single { FirebaseCrashlytics.getInstance() }
    single { Firebase.storage }
    factory<StoreRepositoryImpl>() bind StoreRepository::class
    factory<AuthRepositoryImpl>() bind AuthRepository::class
    factory<StorageRepositoryImpl>() bind StorageRepository::class
}

val useCaseModule = module {
    factory<StoreUseCaseImpl>() bind StoreUseCase::class
    factory<AuthUseCaseImpl>() bind AuthUseCase::class
    factory<StorageUseCaseImpl>() bind StorageUseCase::class
}