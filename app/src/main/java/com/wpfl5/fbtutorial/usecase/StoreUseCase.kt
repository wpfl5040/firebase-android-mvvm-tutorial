package com.wpfl5.fbtutorial.usecase

import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.model.User
import kotlinx.coroutines.flow.Flow

interface StoreUseCase {
    suspend fun getUserList() : FbResponse<List<User>>
    suspend fun updateUser(user: User) : Flow<FbResponse<Boolean>>
    suspend fun deleteUser(userId: String) : Flow<FbResponse<Boolean>>
    suspend fun createUser(user: User) : Flow<FbResponse<Boolean>>
}