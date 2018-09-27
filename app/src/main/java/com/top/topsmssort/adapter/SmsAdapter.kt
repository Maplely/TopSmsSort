package com.top.topsmssort.adapter

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.top.topsmssort.R
import com.top.topsmssort.model.SmsBean
import kotlinx.android.synthetic.main.adapter_sms.view.*

/**
 * Created by lihaitao on 2018/9/27.
 *
 */
class SmsAdapter(val contet: FragmentActivity?, val dataList: ArrayList<SmsBean>) : RecyclerView.Adapter<SmsAdapter.SmsHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int):SmsHolder {
        val view = LayoutInflater.from(contet).inflate(R.layout.fragment_sms, null)
        return SmsHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: SmsHolder, position: Int) {
        val bean = dataList[position]
        holder.where_tv.text = "位置:"+bean.where
        TODO("拨打电话")
        holder.tel_tv.text="电话:"+bean.tel
        holder.name_tv.text="快递柜:"+bean.name
        holder.num_tv.text="快递单号:"+bean.num
        holder.time_tv.text="时间:"+bean.time
    }

    class SmsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var where_tv: TextView=itemView.package_where
        var name_tv: TextView = itemView.package_name
        var tel_tv: TextView=itemView.package_tel
        var num_tv: TextView = itemView.package_num
        var time_tv:TextView=itemView.package_time
    }


}