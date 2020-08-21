package com.wpfl5.fbtutorial.ui.main.analytics

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent

/*
    abd logging
        $adb shell setprop log.tag.FA VERBOSE
        $adb shell setprop log.tag.FA-SVC VERBOSE
        $adb logcat -v time -s FA FA-SVC
 */


/*
    https://firebase.google.com/docs/analytics/get-started?hl=ko&platform=android#start_logging_events
    추천 이벤트: com.google.firebase.analytics.FirebaseAnalytics.Event 클래스 참조를 확인하세요.
    사전 정의된 매개변수: com.google.firebase.analytics.FirebaseAnalytics.Param 참조를 확인하세요.
 */

class AnalyticsViewModel(
    private val analytics: FirebaseAnalytics
) : ViewModel() {

    fun selectEvent() {
        val id = "testId"
        val name = "testName"
        analytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, id)
            param(FirebaseAnalytics.Param.ITEM_NAME, name)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
        }
    }



    fun customEvent(eventName: String) {
        analytics.logEvent(eventName, Bundle())
    }

    


}