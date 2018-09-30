package com.top.topsmssort.fragment

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.top.topsmssort.R
import com.top.topsmssort.activity.MainActivity
import com.top.topsmssort.adapter.SmsAdapter
import com.top.topsmssort.model.PreSmsBean
import com.top.topsmssort.model.SmsBean
import com.top.topsmssort.parse.ParseManager
import com.top.topsmssort.utils.RxUtil
import com.top.topsmssort.utils.log
import com.top.topsmssort.utils.toast
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_sms.view.*

/**
 * Created by lihaitao on 2018/9/27.
 *
 */
class SmsFragment : Fragment() {
    lateinit var dataList: ArrayList<SmsBean>
    lateinit var mAdapter: SmsAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sms, container,false)
        view.sms_toolbar.setNavigationOnClickListener {
            (activity as MainActivity).openDrawer()
        }
        view.sms_float_button.setOnClickListener {
            log("开始处理短信")
            produce()
        }
        view.sms_float_up.setOnClickListener {
            //点击回到顶部
          view.sms_recycler.smoothScrollToPosition(0)
        }
        dataList = arrayListOf()
        mAdapter = SmsAdapter(activity, dataList)
        view.sms_recycler.adapter = mAdapter
        view.sms_recycler.layoutManager=LinearLayoutManager(activity)
        return view
    }



    private fun produce() {
        var dis: Disposable? = null
        var list = mutableListOf<SmsBean>()
        Observable.create<PreSmsBean> {
            val uri = Uri.parse("content://sms/inbox")
            val cur = activity?.contentResolver!!.query(uri, null, null, null, null)

            cur.moveToFirst()
            val date_index = cur.getColumnIndex("date")
            val body_index = cur.getColumnIndex("body")
            while (cur.moveToNext()) {
                val date = cur.getString(date_index)
                val body = cur.getString(body_index)
                val preSmsBean = PreSmsBean(date, body)
                it.onNext(preSmsBean)
            }
            cur.close()
            it.onComplete()
        }.subscribeOn(Schedulers.newThread())
                .map { t -> ParseManager.parseSMs(t) }
                .compose(RxUtil.IO2Main())
                .subscribe(object : Observer<SmsBean> {
                    override fun onComplete() {
                        refresh(list)
                        log("完成解析")
                        toast("解析完成！")
                    }

                    override fun onSubscribe(d: Disposable) {
                        dis = d
                        log("开始解析")
                    }

                    override fun onNext(t: SmsBean) {
                        t.name?.let {
                            list.add(t)
                        }
                    }

                    override fun onError(e: Throwable) {
                        throw e
                    }

                })
    }

    /**
     * 刷新数据
     */
    fun refresh(list: MutableList<SmsBean>) {
        mAdapter.addDate(list as ArrayList<SmsBean>)
        mAdapter.notifyDataSetChanged()
    }
}