package com.wpfl5.fbtutorial.ui.main.storage

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.usecase.StorageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class StorageViewModel(
    private val useCase: StorageUseCase
) : ViewModel() {

    fun saveImg(filePath: Uri) = liveData(Dispatchers.IO) {
        emit(FbResponse.Loading())
        try {
            useCase.saveImgFile(filePath).collect { emit(it) }
        }catch (e: Exception){
            emit(FbResponse.Fail(e))
            Log.e("ERROR", e.message)
        }
    }

    fun getList() = liveData(Dispatchers.IO){
        emit(FbResponse.Loading())
        try {
            useCase.getStorageList().collect { emit(it) }
        }catch (e: Exception){
            emit(FbResponse.Fail(e))
            Log.e("ERROR", e.message)
        }
    }

    fun deleteFile(path: String) = liveData(Dispatchers.IO){
        emit(FbResponse.Loading())
        try {
            useCase.deleteFile(path).collect { emit(it) }
        }catch (e: Exception){
            emit(FbResponse.Fail(e))
            Log.e("ERROR", e.message)
        }
    }

    fun downloadFile(path: String) = liveData(Dispatchers.IO){
        emit(FbResponse.Loading())
        try {
            useCase.downloadFile(path).collect { emit(it) }
        }catch (e: Exception){
            emit(FbResponse.Fail(e))
            Log.e("ERROR", e.message)
        }
    }
}