package com.wpfl5.fbtutorial.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FieldValue

data class User(
    @DocumentId
    val id: String,
    val email: String,
    val name: String,
    val created: Timestamp?
) {
    constructor() : this("", "", "", null)
    constructor(email: String, name: String) : this("", email, name, Timestamp.now())
}