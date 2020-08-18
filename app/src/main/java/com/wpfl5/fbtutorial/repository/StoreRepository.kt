package com.wpfl5.fbtutorial.repository

import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.model.User
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun getUserList() : FbResponse<List<User>>
    suspend fun updateUser(user: User) : Flow<FbResponse<Boolean>>
    suspend fun deleteUser(userId: String) : Flow<FbResponse<Boolean>>
    suspend fun createUser(user: User) : Flow<FbResponse<Boolean>>
}