package com.wpfl5.fbtutorial.usecase

import com.wpfl5.fbtutorial.model.AuthUser
import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthUseCaseImpl(
    private val repository: AuthRepository
) : AuthUseCase {
    override suspend fun register(authUser: AuthUser): Flow<FbResponse<Boolean>> = repository.register(authUser)
    override suspend fun login(authUser: AuthUser): Flow<FbResponse<Boolean>> = repository.login(authUser)
    override suspend fun resetPassword(email: String): Flow<FbResponse<Boolean>> = repository.resetPassword(email)
}