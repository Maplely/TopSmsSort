package com.top.topsmssort.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.top.topsmssort.R

/**
 * Created by lihaitao on 2018/9/27.
 *
 */
class AboutFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about, null)
        return view
    }
}