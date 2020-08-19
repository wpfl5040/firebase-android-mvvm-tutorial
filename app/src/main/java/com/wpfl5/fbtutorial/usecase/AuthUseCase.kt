package com.wpfl5.fbtutorial.usecase

import com.wpfl5.fbtutorial.model.AuthUser
import com.wpfl5.fbtutorial.model.FbResponse
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    suspend fun register(authUser: AuthUser) : Flow<FbResponse<Boolean>>
    suspend fun login(authUser: AuthUser) : Flow<FbResponse<Boolean>>
    suspend fun resetPassword(email: String) : Flow<FbResponse<Boolean>>
}