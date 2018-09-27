package com.top.topsmssort.utils

import android.Manifest
import android.content.Context
import java.io.File

/**
 * Created by lihaitao on 2018/9/26.
 *
 */
object Constants {
    val Permissions = arrayListOf<String>(Manifest.permission.READ_SMS, Manifest.permission.WRITE_EXTERNAL_STORAGE
            , Manifest.permission.READ_EXTERNAL_STORAGE)
    val GIT_URL = "https://raw.githubusercontent.com"
    val debuggable = true
    fun getBackImage(context: Context): String {
        return context.externalCacheDir.absolutePath + File.separator + "background" + File.separator + "back.jpg"
    }
    object type{
        val TYPE_SMS="sms"
        val TYPE_ABOUT="about"
    }
}