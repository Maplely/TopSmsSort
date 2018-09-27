package com.top.topsmssort.fragment

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
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
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_sms.*
import kotlinx.android.synthetic.main.fragment_sms.view.*

/**
 * Created by lihaitao on 2018/9/27.
 *
 */
class SmsFragment : Fragment() {
    lateinit var dataList: ArrayList<SmsBean>
    lateinit var mAdapter: SmsAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sms, null)
        view.sms_toolbar.setNavigationOnClickListener {
            (activity as MainActivity).openDrawer()
        }
        view.sms_float_button.setOnClickListener {
            log("开始处理短信")
        }
        dataList = arrayListOf()
        mAdapter = SmsAdapter(activity, dataList)
        sms_recycler.adapter =
                return view
    }


    private fun produce() {
        Observable.create<PreSmsBean> {
            val uri = Uri.parse("content://sms/inbox")
            val cur = activity?.contentResolver!!.query(uri, null, null, null, null)

            cur.moveToFirst()
            val date_index = cur.getColumnIndex("date")
            val body_index = cur.getColumnIndex("body")
            val address_index = cur.getColumnIndex("address")
            while (cur.moveToNext()) {
                val date = cur.getString(date_index)
                val body = cur.getString(body_index)
                val preSmsBean = PreSmsBean(date, body)
                it.onNext(preSmsBean)
            }
            cur.close()
            it.onComplete()
        }.subscribeOn(Schedulers.newThread())
                .map(object : io.reactivex.functions.Function<PreSmsBean, SmsBean> {
                    override fun apply(t: PreSmsBean): SmsBean {
                       return ParseManager.parseSMs(t)
                    }
                })
                .compose(RxUtil.IO2Main())
                .subscribe(object : Observer<SmsBean> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: SmsBean) {
                    }

                    override fun onError(e: Throwable) {

                    }

                })
    }

    /**
     * 刷新数据
     */
    fun refresh() {
        mAdapter.notifyDataSetChanged()
    }
}