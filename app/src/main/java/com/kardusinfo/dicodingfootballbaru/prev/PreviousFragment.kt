package com.kardusinfo.dicodingfootballbaru.prev

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.gson.Gson
import com.kardusinfo.dicodingfootballbaru.R
import com.kardusinfo.dicodingfootballbaru.api.ApiRepository
import com.kardusinfo.dicodingfootballbaru.info.InfoActivity
import com.kardusinfo.dicodingfootballbaru.model.Events
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PreviousFragment : Fragment(), AnkoComponent<Context>, PreviousView {
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerViewPrev: RecyclerView
    private var mList: MutableList<Events> = mutableListOf()
    private lateinit var presenter: PreviousPresenter
    private lateinit var mAdapter: PreviousAdapter
    private val TAG: String = "PreviousFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        mAdapter = PreviousAdapter(ctx, mList) {
            val i = Intent(context, InfoActivity::class.java)
            i.putExtra("event_id", it.idEvent)
            startActivity(i)
        }
        recyclerViewPrev.adapter = mAdapter
        presenter = PreviousPresenter(this, request, gson)
        swipeRefresh.onRefresh {
            presenter.getMatchList("4328")
        }
        swipeRefresh.isRefreshing = true
        presenter.getMatchList("4328")
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    recyclerViewPrev = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                }
            }
        }
    }

    override fun showMatchList(data: List<Events>) {
        swipeRefresh.isRefreshing = false
        mList.clear()
        mList.addAll(data)
        mAdapter.notifyDataSetChanged()
    }
}
