package com.wpfl5.fbtutorial.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wpfl5.fbtutorial.repository.AuthRepository
import com.wpfl5.fbtutorial.repository.AuthRepositoryImpl
import com.wpfl5.fbtutorial.repository.StoreRepository
import com.wpfl5.fbtutorial.repository.StoreRepositoryImpl
import com.wpfl5.fbtutorial.ui.main.MainViewModel
import com.wpfl5.fbtutorial.ui.main.auth.AuthViewModel
import com.wpfl5.fbtutorial.ui.main.crashlytics.CrashlyticsViewModel
import com.wpfl5.fbtutorial.ui.main.firestore.FireStoreViewModel
import com.wpfl5.fbtutorial.usecase.AuthUseCase
import com.wpfl5.fbtutorial.usecase.AuthUseCaseImpl
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
    viewModel { AuthViewModel(get()) }
    viewModel { CrashlyticsViewModel(get()) }
}

val repoModule = module {
    single { Firebase.firestore }
    single { Firebase.auth }
    single { FirebaseCrashlytics.getInstance() }
    factory<StoreRepositoryImpl>() bind StoreRepository::class
    factory<AuthRepositoryImpl>() bind AuthRepository::class
}

val useCaseModule = module {
    factory<StoreUseCaseImpl>() bind StoreUseCase::class
    factory<AuthUseCaseImpl>() bind AuthUseCase::class
}