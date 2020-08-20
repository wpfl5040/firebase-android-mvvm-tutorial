package com.wpfl5.fbtutorial.utils


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.firebase.Timestamp
import com.google.firebase.storage.StorageReference
import com.wpfl5.fbtutorial.di.GlideApp
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapters {

    @Suppress("unused")
    @BindingAdapter("goneUnless")
    @JvmStatic
    fun goneUnless(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @BindingAdapter("timestampToString")
    @JvmStatic
    fun timestampToString(view: TextView, timestamp: Timestamp?){
        if(timestamp != null){
            val milliseconds = timestamp.seconds * 1000 + timestamp.nanoseconds / 1000000
            val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.KOREA)
            val netDate = Date(milliseconds)
            val date = sdf.format(netDate).toString()
            if(date.isEmpty()) view.text = ""
            else view.text = date
        }
    }

    @BindingAdapter("fbImageWithUi")
    @JvmStatic
    fun fbImageWithUi(view: ImageView, path: StorageReference){
        GlideApp.with(view.context)
            .load(path)
            .into(view)
    }


}





