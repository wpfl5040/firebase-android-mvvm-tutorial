package com.wpfl5.fbtutorial.ui.main.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wpfl5.fbtutorial.model.AuthUser
import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.usecase.AuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class AuthViewModel(
    private val useCase: AuthUseCase
) : ViewModel() {

    fun register(authUser: AuthUser) = liveData(Dispatchers.IO) {
        emit(FbResponse.Loading())
        try {
            useCase.register(authUser).collect { emit(it) }
        }catch (e: Exception){
            emit(FbResponse.Fail(e))
            Log.e("ERROR : ", e.message)
        }
    }

    fun login(authUser: AuthUser) = liveData(Dispatchers.IO) {
        emit(FbResponse.Loading())
        try {
            useCase.login(authUser).collect { emit(it) }
        }catch (e: Exception){
            emit(FbResponse.Fail(e))
            Log.e("ERROR : ", e.message)
        }
    }

    fun resetPassword(email: String) = liveData(Dispatchers.IO) {
        emit(FbResponse.Loading())
        try {
            useCase.resetPassword(email).collect { emit(it) }
        }catch (e: Exception){
            emit(FbResponse.Fail(e))
            Log.e("ERROR : ", e.message)
        }
    }


}