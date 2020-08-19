package com.wpfl5.fbtutorial.ui.main.firestore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.model.User
import com.wpfl5.fbtutorial.usecase.StoreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import java.lang.Exception

class FireStoreViewModel(
    private val useCase: StoreUseCase
): ViewModel() {
    fun userList() = liveData(Dispatchers.IO) {
        emit(FbResponse.Loading())
        try {
            val result = useCase.getUserList()
            emit(result)
        }catch (e: Exception){
            emit(FbResponse.Fail(e))
            Log.e("ERROR : ", e.message)
        }
    }


    fun updateUser(user: User) = liveData(Dispatchers.IO) {
        emit(FbResponse.Loading())
        try {
            useCase.updateUser(user).collect {
                emit(it)
            }
        }catch (e: Exception){
            emit(FbResponse.Fail(e))
            Log.e("ERROR : ", e.message)
        }
    }

    fun deleteUser(userId: String) = liveData(Dispatchers.IO) {
        emit(FbResponse.Loading())
        try {
            useCase.deleteUser(userId).collect{ emit(it) }
        }catch (e: Exception){
            emit(FbResponse.Fail(e))
            Log.e("ERROR : ", e.message)
        }
    }

    fun createUser(user: User) = liveData(Dispatchers.IO) {
        emit(FbResponse.Loading())
        try {
            useCase.createUser(user).collect{ emit(it) }
        }catch (e: Exception){
            emit(FbResponse.Fail(e))
            Log.e("ERROR : ", e.message)
        }
    }


}