package com.top.topsmssort.net

import com.top.topsmssort.api.ApiManager
import com.top.topsmssort.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by lihaitao on 2018/9/26.
 *
 */
object NetFactory  {
    fun getManager():ApiManager{
        val client = OkHttpClient.Builder().connectTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(5000, TimeUnit.SECONDS)
                .build()
        val retrofit = Retrofit.Builder().baseUrl(Constants.GIT_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        return  retrofit.create(ApiManager::class.java)
    }

}