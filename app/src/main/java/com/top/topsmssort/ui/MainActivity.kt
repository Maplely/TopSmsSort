package com.top.topsmssort.ui

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.top.topsmssort.R
import com.top.topsmssort.utils.Constants
import com.top.topsmssort.utils.TimeUtil
import com.top.topsmssort.utils.log
import com.top.topsmssort.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        val permission_request = 0x10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkoutPermisson()
        bt.setOnClickListener {

        }
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

    /**
     * 检查权限
     */
    private fun checkoutPermisson() {
        var no_list = mutableListOf<String>()
        Constants.Permissions.forEach {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, it)) {
                no_list.add(it)
            }
        }
        if (no_list.size > 0) {
            ActivityCompat.requestPermissions(this, no_list.toTypedArray(), permission_request)
        }
    }

    /**
     * 权限回调
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permission_request) {
            grantResults.forEach {
                if (it != PackageManager.PERMISSION_DENIED) {
                    //权限没有通过
                    toast("请在设置中开启权限,否则无法正常使用app")
                }
            }
        }
    }
}
