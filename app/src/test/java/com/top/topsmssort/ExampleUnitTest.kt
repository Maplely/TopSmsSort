package com.top.topsmssort

import org.junit.Test
import java.util.regex.Pattern

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        var s="【丰巢】凭取件码『62107532』至南海家园二里东门丰巢快递柜取中通快递的包裹！"
        var a="【菜鸟智能柜】凭取件码563334到菜鸟『智能柜』取包裹。南海家园二里东门入口右侧，快递员15110259680，找不到智能柜可查看地图指引:http://m.tb.cn/2.ZvKGY，裹裹领红包 tb.cn/zCZIlPw"
        val matcher = Pattern.compile("『[0-9]+』").matcher(s)
        if(matcher.matches()){
            val group = matcher.group()
        }
    }
}
