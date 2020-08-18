package com.wpfl5.fbtutorial.usecase

import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.model.User
import com.wpfl5.fbtutorial.repository.StoreRepository
import kotlinx.coroutines.flow.Flow

class StoreUseCaseImpl(
    private val storeRepository: StoreRepository
) : StoreUseCase {
    override suspend fun getUserList(): FbResponse<List<User>> = storeRepository.getUserList()
    override suspend fun updateUser(user: User): Flow<FbResponse<Boolean>> = storeRepository.updateUser(user)
    override suspend fun deleteUser(userId: String): Flow<FbResponse<Boolean>> = storeRepository.deleteUser(userId)
    override suspend fun createUser(user: User): Flow<FbResponse<Boolean>> = storeRepository.createUser(user)
}