package com.top.topsmssort.api

import com.top.topsmssort.model.ConfigBean
import io.reactivex.Observable
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by lihaitao on 2018/9/26.
 *
 */
interface ApiManager {
//    https://raw.githubusercontent.com/top2015/Resource/master/image_sms/config.json
    @GET("top2015/Resource/master/image_sms/config.json")
    fun getConfig():Observable<ConfigBean>
    @GET
    fun getImage(@Url url:String):Observable<Response>
}