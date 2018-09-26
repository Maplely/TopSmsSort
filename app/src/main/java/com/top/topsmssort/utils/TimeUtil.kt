package com.top.topsmssort.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by lihaitao on 2018/9/26.
 *
 */
object TimeUtil {
    fun TimeConvert(time: String): String {
        val format = "yyyy-MM-dd HH:mm:ss"
        val dateFormat = SimpleDateFormat(format)
        return dateFormat.format(Date(time.toLong()))
    }
}