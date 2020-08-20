package com.wpfl5.fbtutorial.model

import com.google.firebase.storage.StorageReference

data class StorageResponse (
    val name: String,
    val path: String,
    val ref: StorageReference
)

