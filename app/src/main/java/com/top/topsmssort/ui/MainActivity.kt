package com.top.topsmssort.ui

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.top.topsmssort.R
import com.top.topsmssort.utils.TimeUtil
import com.top.topsmssort.utils.log

class MainActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        val permission_request = 0x10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * 读取短信
     */
    private fun readSms() {
        val uri = Uri.parse("content://sms/inbox")
        val cur = contentResolver.query(uri, null, null, null, null)
        cur.moveToFirst()
        val date_index = cur.getColumnIndex("date")
        val body_index = cur.getColumnIndex("body")
        val address_index = cur.getColumnIndex("address")
        val builder = StringBuilder()
        while (cur.moveToNext()) {
            if (builder.isNotEmpty()) {
                builder.delete(0, builder.length)
            }
            val date = cur.getString(date_index)
            val body = cur.getString(body_index)
            val address = cur.getString(address_index)
            builder.append("日期:").append(TimeUtil.TimeConvert(date)).append(",内容:").append(body).append(",电话号码:").append(address)
                    .append("\n")
            log(builder.toString())
        }
        cur.close()
    }


}
