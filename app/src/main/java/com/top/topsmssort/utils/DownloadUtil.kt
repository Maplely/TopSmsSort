package com.top.topsmssort.utils

import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

/**
 * Created by lihaitao on 2018/9/26.
 *
 */
object DownloadUtil {
    fun down(body: ResponseBody, file: File) {

        var input: InputStream? = null
        var fileOutputStream: FileOutputStream? = null
        try {
            input = body.byteStream()
            fileOutputStream = FileOutputStream(file)

            var count: Int = 0
            count = input.read()
            while (count != -1) {
                fileOutputStream.write(count)
            }

        } catch (e: Exception) {

        } finally {
            if (input != null) {
                input.close()
            }
            if (fileOutputStream != null) {
                fileOutputStream.close()
            }
        }
    }
}