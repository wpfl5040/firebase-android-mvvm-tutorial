package com.wpfl5.fbtutorial.repository

import com.google.firebase.auth.FirebaseAuth
import com.wpfl5.fbtutorial.model.AuthUser
import com.wpfl5.fbtutorial.model.FbResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.koin.core.KoinComponent
import org.koin.core.inject

class AuthRepositoryImpl : AuthRepository, KoinComponent {
    private val auth: FirebaseAuth by inject()


    @ExperimentalCoroutinesApi
    override suspend fun register(authUser: AuthUser): Flow<FbResponse<Boolean>> = callbackFlow {
        auth.createUserWithEmailAndPassword(authUser.email, authUser.password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    offer(FbResponse.Success(true))
                }
            }.addOnFailureListener {
                offer(FbResponse.Fail(it))
            }

        awaitClose { this.cancel() }
    }

    @ExperimentalCoroutinesApi
    override suspend fun login(authUser: AuthUser): Flow<FbResponse<Boolean>> = callbackFlow {
        auth.signInWithEmailAndPassword(authUser.email, authUser.password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    offer(FbResponse.Success(true))
                }
            }.addOnFailureListener {
                offer(FbResponse.Fail(it))
            }

        awaitClose { this.cancel() }
    }

    @ExperimentalCoroutinesApi
    override suspend fun resetPassword(email: String): Flow<FbResponse<Boolean>> = callbackFlow {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    offer(FbResponse.Success(true))
                }
            }.addOnFailureListener {
                offer(FbResponse.Fail(it))
            }
        awaitClose { this.cancel() }
    }
}