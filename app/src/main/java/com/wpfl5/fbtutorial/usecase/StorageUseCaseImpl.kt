package com.wpfl5.fbtutorial.usecase

import android.net.Uri
import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.model.StorageResponse
import com.wpfl5.fbtutorial.repository.StorageRepository
import kotlinx.coroutines.flow.Flow

class StorageUseCaseImpl(
    private val repository: StorageRepository
) : StorageUseCase {
    override suspend fun saveImgFile(filePath: Uri): Flow<FbResponse<Boolean>> = repository.saveImgFile(filePath)
    override suspend fun getStorageList(): Flow<FbResponse<List<StorageResponse>>> = repository.getStorageList()
    override suspend fun deleteFile(filePath: String) : Flow<FbResponse<Boolean>> = repository.deleteFile(filePath)
    override suspend fun downloadFile(filePath: String): Flow<FbResponse<Boolean>> = repository.downloadFile(filePath)
}