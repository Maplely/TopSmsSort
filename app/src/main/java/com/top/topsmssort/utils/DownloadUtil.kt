package com.top.topsmssort.utils

import android.util.Log
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream

/**
 * Created by lihaitao on 2018/9/26.
 *
 */
object DownloadUtil {
    fun down(body: ResponseBody, file: File) {


        var fileOutputStream: FileOutputStream? = null
        try {
            val input = body.bytes()
            fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(input)

        } catch (e: Exception) {
            if (Constants.debuggable) {
                Log.e("TTT", "出现错误" + e.message)
            }
        } finally {

            if (fileOutputStream != null) {
                fileOutputStream.close()
            }
        }
    }
}