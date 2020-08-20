package com.wpfl5.fbtutorial.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.model.StorageResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

//TODO : Modify rule

@ExperimentalCoroutinesApi
class StorageRepositoryImpl : StorageRepository, KoinComponent {
    private val storage :FirebaseStorage by inject()

    override suspend fun saveImgFile(filePath: Uri): Flow<FbResponse<Boolean>> = callbackFlow {

        //TODO : Please modify the path Url
        val storageRef = storage.getReferenceFromUrl("Enter your url")


        //파일이름
        val fileName =
            SimpleDateFormat("MM월-dd일-HH:mm:ss", Locale.KOREA).format(System.currentTimeMillis())
        //val file = Uri.fromFile(imgFile)

        storageRef.child("imageFolder/")
            .child(fileName)
            .putFile(filePath)
            .addOnCompleteListener {
                offer(FbResponse.Success(true))
            }.addOnFailureListener {
                offer(FbResponse.Fail(it))
            }


        awaitClose{ this.cancel() }
    }


    override suspend fun getStorageList(): Flow<FbResponse<List<StorageResponse>>> = callbackFlow {

        //TODO : Please modify the path appropriately
        val listRef = storage.reference.child("Enter your folder")

        listRef.listAll()
            .addOnSuccessListener { listResult ->
                val list = mutableListOf<StorageResponse>()
            listResult.prefixes.forEach { prefix ->
                // All the prefixes under listRef.
                // You may call listAll() recursively on them.
            }

            listResult.items.forEach { item ->
                // All the items under listRef.
                val name = item.name
                val path = item.path
                list.add(StorageResponse(name, path, item))
            }
                offer(FbResponse.Success(list))
        }.addOnFailureListener {
                // Uh-oh, an error occurred!
                offer(FbResponse.Fail(it))
        }


        awaitClose { this.cancel() }
    }

    override suspend fun deleteFile(filePath: String): Flow<FbResponse<Boolean>> = callbackFlow {
        val desertRef = storage.reference.child(filePath)

        // Delete the file
        desertRef.delete().addOnSuccessListener {
            // File deleted successfully
            offer(FbResponse.Success(true))
        }.addOnFailureListener {
            // Uh-oh, an error occurred!
            offer(FbResponse.Fail(it))
        }

        awaitClose { this.cancel() }
    }

    override suspend fun downloadFile(filePath: String): Flow<FbResponse<Boolean>> = callbackFlow {
        val islandRef = storage.reference.child(filePath)
        val localFile = File.createTempFile("images", "jpg")

        islandRef.getFile(localFile).addOnSuccessListener {
            // Local temp file has been create
            Log.d("//bytesTransferred",it.bytesTransferred.toString())
            Log.d("//totalCount",it.totalByteCount.toString())
            offer(FbResponse.Success(true))
        }.addOnProgressListener {

        }.addOnFailureListener{
            // Handle any errors
            offer(FbResponse.Fail(it))
        }

        awaitClose { this.cancel() }

    }


}