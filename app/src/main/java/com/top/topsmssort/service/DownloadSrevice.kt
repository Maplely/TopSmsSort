package com.top.topsmssort.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.top.topsmssort.net.NetFactory
import com.top.topsmssort.utils.DownloadUtil
import com.top.topsmssort.utils.RxUtil
import com.top.topsmssort.utils.log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.File

/**
 * Created by lihaitao on 2018/9/26.
 *
 */
class DownloadSrevice : Service() {
    val imag_path = externalCacheDir.absolutePath + File.separator + "background";
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("服务开始")
        val file = makeDir()
        getUrlandPic(file)
        return Service.START_STICKY

    }

    private fun makeDir(): File {
        val file = File(imag_path)
        if (!file.exists()) {
            file.mkdirs()
        }
        return File(file, System.currentTimeMillis().toString() + ".jpg")
    }

    /**
     * 获取url 并解析 储存图片
     */
    private fun getUrlandPic(file: File) {

        val api = NetFactory.getManager()
        var dispose: Disposable? = null
        api.getConfig().subscribeOn(Schedulers.newThread())
                .flatMap { t -> api.getImage(t.pic_url) }
                .map { t -> DownloadUtil.down(t.body()!!, file) }
                .compose(RxUtil.IO2Main())
                .subscribe(object : Observer<Unit> {
                    override fun onComplete() {
                        log("完成")
                        dispose?.dispose()
                        stopSelf()
                    }

                    override fun onSubscribe(d: Disposable) {
                        dispose = d
                    }

                    override fun onNext(t: Unit) {

                    }

                    override fun onError(e: Throwable) {
                        log("出现错误:" + e.message)

                    }

                })
    }
}