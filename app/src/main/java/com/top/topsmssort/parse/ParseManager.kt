package com.top.topsmssort.parse

import com.top.topsmssort.model.PreSmsBean
import com.top.topsmssort.model.SmsBean
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * Created by lihaitao on 2018/9/27.
 *
 */
object ParseManager {
    const val NUM = "num"
    const val WHERE = "where"
    const val PHONE = "phone"
    val format = "yyyy年MM月dd日-hh:mm:ss"
    fun parseSMs(sms: PreSmsBean): SmsBean {
        val smsBean = SmsBean()

        val time = SimpleDateFormat(format).format(Date(sms.data.toLong()))
        val body = sms.body
        if (!body.contains("】")) {
            return smsBean
        }
        val str = body.substring(1, body.indexOfFirst { it == '】' })
        when (str) {
            "菜鸟智能柜" -> {
                smsBean.name = str
                val dataMap = parseCaiNiao(body)
                smsBean.time = time
                smsBean.num = dataMap.get(NUM)
                smsBean.tel = dataMap.get(PHONE)
                smsBean.where = dataMap.get(WHERE)
            }
            "丰巢" -> {
                smsBean.name = str
                val dataMap = parseFengChao(body)
                smsBean.num = dataMap.get(NUM)
                smsBean.where = dataMap.get(WHERE)
                smsBean.time = time
            }
            "e栈" -> {
                smsBean.name = str
                val map = parseEZhan(body)
                smsBean.num = map.get(NUM)
                smsBean.where = map.get(WHERE)
            }
            "中国邮政" -> {
                smsBean.name = str
                val map = parseChina(body)
                smsBean.num=map.get(NUM)
                smsBean.where=map.get(WHERE)
                smsBean.tel=map.get(PHONE)
            }
            else -> {
                smsBean.num = null
            }
        }
        return smsBean
    }

    /**
     * 解析中国邮政
     */
    private fun parseChina(body: String): Map<String, String> {
        var map = mutableMapOf<String, String>()
        var matcher = Pattern.compile("快凭密码\\d").matcher(body)
        if (matcher.find()) {
            val group = matcher.group()
            group.let {
                map.put(NUM, it.substring(4, it.length))
            }
        }
        matcher = Pattern.compile("投递员\\d").matcher(body)
        if (matcher.find()) {
            val group = matcher.group()
            group.let {
                map.put(PHONE, it)
            }
        }
        matcher = Pattern.compile("到?易邮柜").matcher(body)
        if (matcher.find()) {
            val group = matcher.group()
            group.let {
                map.put(WHERE, it)
            }
        }
        return map
    }


    /**
     * 解析e栈
     */
    private fun parseEZhan(body: String): Map<String, String> {
        val map = mutableMapOf<String, String>()
        var matcher = Pattern.compile("南海家园?e栈").matcher(body)
        if (matcher.find()) {
            val s = matcher.group()
            map.put(WHERE, s)
        }
        matcher = Pattern.compile("取件码(\\d)").matcher(body)
        if (matcher.find()) {
            val s = matcher.group()
            s.let {
                map.put(NUM, it.substring(3, it.length))
            }
        }
        return map
    }

    /**
     * 菜鸟解析
     */
    private fun parseCaiNiao(body: String): Map<String, String> {
        var map = mutableMapOf<String, String>()
        var matcher = Pattern.compile("取件码\\d").matcher(body)
        if (matcher.find()) {
            val group = matcher.group()
            val s = group.let {
                it.substring(3, it.length - 1)
            }
            map.put(NUM, s)
        }
        matcher = Pattern.compile("快递员\\d").matcher(body)
        if (matcher.find()) {
            val group = matcher.group()
            val s = group.let {
                it.substring(3, it.length - 1)
            }
            map.put(PHONE, s)
        }
        matcher = Pattern.compile("南海家园?菜鸟").matcher(body)
        if (matcher.find()) {
            val s = matcher.group()
            val w = s.let {
                s.substring(0, it.length - 2)
            }
            map.put(WHERE, w)
        }
        return map
    }

    /**
     *丰巢解析
     */
    //1 where 2num
    fun parseFengChao(input: String): Map<String, String> {
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