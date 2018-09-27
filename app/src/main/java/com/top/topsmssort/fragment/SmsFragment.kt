package com.top.topsmssort.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.top.topsmssort.R
import com.top.topsmssort.activity.MainActivity
import com.top.topsmssort.utils.toast
import kotlinx.android.synthetic.main.fragment_sms.view.*

/**
 * Created by lihaitao on 2018/9/27.
 *
 */
class SmsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sms, null)
        view.sms_toolbar.setNavigationOnClickListener {
            (activity as MainActivity).openDrawer()
        }
        view.sms_float_button.setOnClickListener {
            toast("被点击")
        }
        return view
    }
}