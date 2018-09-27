package com.top.topsmssort.parse

import com.top.topsmssort.model.PreSmsBean
import com.top.topsmssort.model.SmsBean
import java.util.regex.Pattern

/**
 * Created by lihaitao on 2018/9/27.
 *
 */
object ParseManager {
    fun parseSMs(sms: PreSmsBean): SmsBean {
        val time = sms.data
        val body = sms.body
        var name: String? = null
        var where: String? = null
        var num: String? = null
        var tel: String? = null
        val str = body.substring(1, body.indexOfFirst { it == '】' })
        when (str) {
            "菜鸟智能柜" -> {
                name=str

            }
            "蜂巢" -> {
                name=str
            }
            "e栈" -> {
                name=str
            }
            "云喇叭" -> {
                name=str
            }
            "中国邮政" -> {
                name=str
            }
            else -> {

            }

        }

        return substring
    }
    //1 where 2num 3tel
    fun parseCaiNiao(input:String):ArrayList<String>{
        input.contains("取件码",true)
        Pattern.compile("『[0-9]+』")
    }

}