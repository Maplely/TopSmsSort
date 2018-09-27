package com.top.topsmssort.ui

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.top.topsmssort.R
import com.top.topsmssort.utils.TimeUtil
import com.top.topsmssort.utils.log
import com.top.topsmssort.utils.toast
import kotlinx.android.synthetic.main.activity_m.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m)
        drawer_navigation.setNavigationItemSelectedListener(object:NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(p0: MenuItem): Boolean {
                val itemId = p0.itemId
                when(itemId){
                    R.id.sms->{
                        //navigation菜单
                        toast("sms")
                        sms_drawer.closeDrawers()
                    }
                    R.id.about->{
                        toast("about")
                        sms_drawer.closeDrawers()
                    }
                }
                return true
            }

        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.drawer_menu,menu)
        return true
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


}
