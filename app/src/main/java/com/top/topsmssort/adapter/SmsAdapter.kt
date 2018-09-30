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
import kotlinx.android.synthetic.main.sms_adapter_header.view.*

/**
 * Created by lihaitao on 2018/9/27.
 *
 */
class SmsAdapter(val contet: FragmentActivity?, var dataList: ArrayList<SmsBean>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_HEADER = 0x1
    private val TYPE_CONTENT = 0x2
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return if (p1 == TYPE_HEADER) {
            val view = LayoutInflater.from(contet).inflate(R.layout.sms_adapter_header, p0, false)
            SmsHeaderHolder(view)
        } else {
            val view = LayoutInflater.from(contet).inflate(R.layout.adapter_sms, p0, false)
            SmsHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return if (dataList == null) 1 else dataList!!.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewType = getItemViewType(position)
        if (itemViewType == TYPE_HEADER) {
            if (dataList?.size!! > 0) {
                holder as SmsHeaderHolder
                holder.headerTitle.visibility = View.VISIBLE
                holder.headerTitle.text = String.format(contet?.resources!!.getString(R.string.sms_adapter_header_str, dataList?.size))
            }
        } else if (itemViewType == TYPE_CONTENT) {
            dataList?.run {
                if (this.size > 0) {
                    holder as SmsHolder
                    val bean = this[position]
                    holder.where_tv.text = "位置:" + bean.where
                    //TODO(拨打电话)
                    holder.tel_tv.text = "电话:" + bean.tel
                    holder.name_tv.text = "快递柜:" + bean.name
                    holder.num_tv.text = "取件码:" + bean.num
                    holder.time_tv.text = "时间:" + bean.time
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else {
            TYPE_CONTENT
        }
    }

    class SmsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val where_tv: TextView = itemView.package_where
        val name_tv: TextView = itemView.package_name
        val tel_tv: TextView = itemView.package_tel
        val num_tv: TextView = itemView.package_num
        val time_tv: TextView = itemView.package_time
    }

    class SmsHeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerTitle: TextView = itemView.adapter_header_title
    }

    fun addDate(list: ArrayList<SmsBean>) {
        dataList?.clear()
        dataList?.addAll(list)
        notifyDataSetChanged()
    }
}