package com.top.topsmssort.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.top.topsmssort.R
import com.top.topsmssort.service.DownloadSrevice
import com.top.topsmssort.utils.Constants
import com.top.topsmssort.utils.toast
import kotlinx.android.synthetic.main.activity_splash.*
import java.io.File

/**
 * Created by lihaitao on 2018/9/26.
 *
 */
class SplashActivity : AppCompatActivity() {
    val permission_request = 0x10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //设置背景图
        setBackPic()
        //权限检查
        checkoutPermisson()
    }

    private fun setBackPic() {
        val path = Constants.getBackImage(this)
        val file = File(path)
        if (file.exists()) {
            //文件存在
            val drawable = BitmapFactory.decodeFile(path)
            splash_back.setImageBitmap(drawable)
        }

    }

    private fun beginService() {
        val intent = Intent(this@SplashActivity, DownloadSrevice::class.java)
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
        } else {
            setAnim()
            beginService()
        }
    }

    /**
     * 权限回调
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var flag = true;
        if (requestCode == permission_request) {
            grantResults.forEach {
                if (it != PackageManager.PERMISSION_GRANTED) {
                    //权限没有通过
                    toast("请在设置中开启权限,否则无法正常使用app")
                    flag = false
                    return@forEach
                }
            }
            if (flag) {
                beginService()
            }
            setAnim()
        }
    }
}