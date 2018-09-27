package com.top.topsmssort.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import com.top.topsmssort.R
import com.top.topsmssort.fragment.AboutFragment
import com.top.topsmssort.fragment.SmsFragment
import com.top.topsmssort.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawer_navigation.setNavigationItemSelectedListener { p0 ->
            val itemId = p0.itemId
            when (itemId) {
                R.id.sms -> {
                    //navigation菜单
                    switch(Constants.type.TYPE_SMS)
                    sms_drawer.closeDrawers()
                }
                R.id.about -> {
                    switch(Constants.type.TYPE_ABOUT)
                    sms_drawer.closeDrawers()
                }
            }
            true
        }
        switch(Constants.type.TYPE_SMS)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val count = supportFragmentManager.backStackEntryCount
        if(count===0){
            super.onBackPressed()
        }else{
            supportFragmentManager.popBackStack()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.drawer_menu, menu)
        return true
    }
    fun openDrawer(){
        sms_drawer.openDrawer(Gravity.START)
    }
    fun closDrawer(){
        sms_drawer.closeDrawers()
    }

    fun switch(type: String) {
        val manager = supportFragmentManager
        var fragment = manager.findFragmentByTag(type)
        if (fragment == null) {
            fragment = when (type) {
                Constants.type.TYPE_SMS -> {
                    SmsFragment()
                }
                Constants.type.TYPE_ABOUT -> {
                    AboutFragment()
                }
                else -> {
                    null
                }
            }
        }
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.sms_acitivity_content,fragment!!,type)
        transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
        transaction.commitAllowingStateLoss()
    }



}
