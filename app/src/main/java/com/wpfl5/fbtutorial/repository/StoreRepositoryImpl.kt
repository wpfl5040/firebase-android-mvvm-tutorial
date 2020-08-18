package com.wpfl5.fbtutorial.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class StoreRepositoryImpl : StoreRepository, KoinComponent {
    private val db: FirebaseFirestore by inject()


    override suspend fun getUserList(): FbResponse<List<User>> =  suspendCoroutine {cont ->
        val userDocument = db.collection("users")

        val userTask = userDocument.get()
            .addOnSuccessListener {
                try {
                    cont.resume(FbResponse.Success(it.toObjects()))
                } catch (e: Exception) {
                    cont.resume(FbResponse.Fail(e))
                }
            }.addOnFailureListener { cont.resume(FbResponse.Fail(it)) }

    }

    @ExperimentalCoroutinesApi
    override suspend fun updateUser(user: User): Flow<FbResponse<Boolean>> = callbackFlow {
        val userDocument = db.collection("users").document(user.id)

        val map = HashMap<String, Any>()
        map["email"] = user.email
        map["name"] = user.name

        val userTask = userDocument.update(map)
            .addOnSuccessListener {
                offer(FbResponse.Success(true))
            }.addOnFailureListener {
                offer(FbResponse.Fail(it))
            }

        awaitClose{ this.cancel() }
    }

    @ExperimentalCoroutinesApi
    override suspend fun deleteUser(userId: String): Flow<FbResponse<Boolean>> = callbackFlow {
        val userDocument = db.collection("users").document(userId)

        val userTask = userDocument.delete()
            .addOnSuccessListener { offer(FbResponse.Success(true)) }
            .addOnFailureListener { offer(FbResponse.Fail(it)) }

        awaitClose { this.cancel() }
    }

    @ExperimentalCoroutinesApi
    override suspend fun createUser(user: User): Flow<FbResponse<Boolean>> = callbackFlow {
        val userDocument = db.collection("users").document()

        val userTask = userDocument.set(user)
            .addOnSuccessListener { offer(FbResponse.Success(true)) }
            .addOnFailureListener { offer(FbResponse.Fail(it)) }

        awaitClose { this.cancel() }
    }

}
