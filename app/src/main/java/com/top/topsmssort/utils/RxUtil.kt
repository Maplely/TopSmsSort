package com.top.topsmssort.utils

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by lihaitao on 2018/9/26.
 *
 */
object RxUtil{
    fun <T> IO2Main(): ObservableTransformer<T,T> {
     return object :ObservableTransformer<T,T>{
         override fun apply(upstream: Observable<T>): ObservableSource<T> {
                return upstream.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
         }

     }
    }
}