package com.wpfl5.fbtutorial.repository

import com.wpfl5.fbtutorial.model.AuthUser
import com.wpfl5.fbtutorial.model.FbResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(authUser: AuthUser) : Flow<FbResponse<Boolean>>
    suspend fun login(authUser: AuthUser) : Flow<FbResponse<Boolean>>
    suspend fun resetPassword(email: String) : Flow<FbResponse<Boolean>>
}