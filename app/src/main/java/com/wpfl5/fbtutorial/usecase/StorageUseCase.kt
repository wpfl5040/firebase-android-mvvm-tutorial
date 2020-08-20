package com.wpfl5.fbtutorial.usecase

import android.net.Uri
import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.model.StorageResponse
import kotlinx.coroutines.flow.Flow

interface StorageUseCase {
    suspend fun saveImgFile(filePath: Uri) : Flow<FbResponse<Boolean>>
    suspend fun getStorageList() : Flow<FbResponse<List<StorageResponse>>>
    suspend fun deleteFile(filePath: String) : Flow<FbResponse<Boolean>>
    suspend fun downloadFile(filePath: String) : Flow<FbResponse<Boolean>>

}