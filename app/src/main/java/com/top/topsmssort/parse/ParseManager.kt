package com.top.topsmssort.parse

import com.top.topsmssort.model.PreSmsBean
import com.top.topsmssort.model.SmsBean
import java.util.regex.Pattern

/**
 * Created by lihaitao on 2018/9/27.
 *
 */
object ParseManager {
    const val NUM = "num"
    const val WHERE = "where"
    const val PHONE = "phone"
    fun parseSMs(sms: PreSmsBean): SmsBean {
        val smsBean = SmsBean()
        val time = sms.data
        val body = sms.body
        if(!body.contains("】")){
            return smsBean
        }
        val str = body.substring(1, body.indexOfFirst { it == '】' })
        when (str) {
            "菜鸟智能柜" -> {
                smsBean.name = str
            }
            "蜂巢" -> {
                smsBean.name = str
                val dataMap = parseCaiNiao(body)
                smsBean.num = dataMap.get(NUM)
                smsBean.where = dataMap.get(WHERE)
                smsBean.tel = dataMap.get(PHONE)
                smsBean.time = time
            }
            "e栈" -> {
                smsBean.name = str
            }
            "云喇叭" -> {
                smsBean.name = str
            }
            "中国邮政" -> {
                smsBean.name = str
            }
            else -> {
                smsBean.name=null
            }

        }
        return smsBean
    }

    /*
    丰巢解析
     */
    //1 where 2num
    fun parseCaiNiao(input: String): Map<String, String> {
        var map = mutableMapOf<String, String>()
        //num
        var matcher = Pattern.compile("『\\d+』").matcher(input)
        if (matcher.find()) {
            val group = matcher.group()
            val s1 = group.let {
                it.substring(1, it.length - 1)
            }
            map.put(NUM, s1)
        }
        matcher = Pattern.compile("至(.+)丰").matcher(input)
        if (matcher.find()) {
            val group = matcher.group()
            val run = group.run {
                this.substring(1, this.length - 1)
            }
            map.put(WHERE, run)
        }
        return map
    }

}