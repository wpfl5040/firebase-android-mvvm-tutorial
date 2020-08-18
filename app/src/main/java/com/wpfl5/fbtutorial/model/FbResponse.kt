package com.wpfl5.fbtutorial.model

import java.lang.Exception

sealed class FbResponse<out T> {
    class Loading<out T> : FbResponse<T>()
    data class Success<out T>(val data: T) : FbResponse<T>()
    data class Fail<out T>(val e: Exception) : FbResponse<T>()
}