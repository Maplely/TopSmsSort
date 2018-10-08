package com.top.topsmssort.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.top.topsmssort.R
import com.top.topsmssort.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_about.view.*

/**
 * Created by lihaitao on 2018/9/27.
 *
 */
class AboutFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about, null)
        view.about_full_screen.setOnClickListener {
          val activity=  (activity as MainActivity)
            if(activity.isDrawerClosed()){
                activity.closDrawer()
            }else{
                activity.openDrawer()
            }
        }
        return view
    }
}