package com.wpfl5.fbtutorial.ui.main.crashlytics

import androidx.lifecycle.ViewModel
import com.google.firebase.crashlytics.FirebaseCrashlytics

class CrashlyticsViewModel(
    private val crash: FirebaseCrashlytics
) : ViewModel() {

    fun testCrash() {
        throw RuntimeException("Test Crash")
    }

    fun setCustomKey(){
        // Set a key to a string.
        crash.setCustomKey("str_key", "hello")

        // Set a key to a boolean.
        crash.setCustomKey("bool_key", true)

        // Set a key to an int.
        crash.setCustomKey("int_key", 1)

        // Set a key to an long.
        crash.setCustomKey("int_key", 1L)

        // Set a key to a float.
        crash.setCustomKey("float_key", 1.0f)

        // Set a key to a double.
        crash.setCustomKey("double_key", 1.0)
    }

}
