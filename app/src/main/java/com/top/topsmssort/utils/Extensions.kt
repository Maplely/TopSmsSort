package com.top.topsmssort.utils

import android.app.Service
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.top.topsmssort.utils.Constants.debuggable

/**
 * Created by lihaitao on 2018/9/26.
 *
 */
fun AppCompatActivity.toast(content: String) {
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.log(content: String) {
    if (debuggable) {
        Log.e("TTT", content)
    }
}
fun Service.log(content: String){
    if(debuggable){
        Log.e("TTT",content)
    }
}
fun Fragment.toast(content: String){
    Toast.makeText(this.activity, content, Toast.LENGTH_SHORT).show()
}
fun Fragment.log(content: String){
    if(debuggable){
        Log.e("TTT",content)
    }
}