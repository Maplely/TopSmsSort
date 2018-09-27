package com.top.topsmssort.utils

import android.content.Context

/**
 * Created by lihaitao on 2018/9/27.
 *
 */
object SpUtil {
    val BACKKEY="background_url"
    fun putBackGroundString(context: Context,url:String){
        val preferences = context.getSharedPreferences("back", Context.MODE_PRIVATE)
        preferences.edit().putString(BACKKEY,url).apply()
    }
    fun  getBackGroundString(context: Context):String{
        return context.getSharedPreferences("back",Context.MODE_PRIVATE).getString(BACKKEY,"null")!!
    }
}