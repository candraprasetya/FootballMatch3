package com.kardusinfo.dicodingfootballbaru.next

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.kardusinfo.dicodingfootballbaru.R
import com.kardusinfo.dicodingfootballbaru.api.ApiRepository
import com.kardusinfo.dicodingfootballbaru.info.InfoActivity
import com.kardusinfo.dicodingfootballbaru.model.Events
import kotlinx.android.synthetic.main.frag_next.*
import org.jetbrains.anko.support.v4.onRefresh

class NextFragment : Fragment(), NextView {
    private var TAG = "NextFragment"
    private lateinit var presenter: NextPresenter
    private var mList: MutableList<Events> = mutableListOf()
    private lateinit var mAdapter: NextAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_next, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = NextPresenter(this, request, gson)
        recyclerViewNext.layoutManager = LinearLayoutManager(activity)
        mAdapter = NextAdapter(activity, mList) {
            val i = Intent(context, InfoActivity::class.java)
            i.putExtra("event_id", it.idEvent)
            startActivity(i)
        }
        recyclerViewNext.adapter = mAdapter
        swipeNext.onRefresh {
            presenter.getMatchList()
        }
        swipeNext.isRefreshing = true
        presenter.getMatchList()
    }

    override fun showMatchList(data: List<Events>) {
        swipeNext.isRefreshing = false
        mList.clear()
        mList.addAll(data)
        mAdapter.notifyDataSetChanged()
    }
}
