package com.top.topsmssort.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.top.topsmssort.R
import com.top.topsmssort.model.ConfigBean
import com.top.topsmssort.net.NetFactory
import com.top.topsmssort.service.DownloadSrevice
import com.top.topsmssort.utils.RxUtil
import com.top.topsmssort.utils.log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by lihaitao on 2018/9/26.
 *
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //开启服务
        beginService()
        //设置动画
        setAnim()
    }

    private fun beginService() {
        Intent(this@SplashActivity, DownloadSrevice::class.java)
        startService(intent)
    }

    private fun setAnim() {
        val loadAnimation = AnimationUtils.loadAnimation(this, R.anim.backgound)
        loadAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                //动画结束开始跳转
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
        splash_back.startAnimation(loadAnimation)
    }

    /**
     * 获取url
     */
    private fun getUrl() {
        val api = NetFactory.getManager()
        var dispose: Disposable? = null
        api.getConfig().compose(RxUtil.IO2Main())
                .subscribe(object : Observer<ConfigBean> {
                    override fun onComplete() {
                        log("完成")
                        dispose?.dispose()
                    }

                    override fun onSubscribe(d: Disposable) {
                        dispose = d
                    }

                    override fun onNext(t: ConfigBean) {
                        log(t.pic_url)
                        //开启服务

                    }

                    override fun onError(e: Throwable) {
                        log("出现错误")
                    }

                })
    }
}