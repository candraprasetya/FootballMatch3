package com.kardusinfo.dicodingfootballbaru.favorites


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kardusinfo.dicodingfootballbaru.R
import com.kardusinfo.dicodingfootballbaru.R.color.colorAccent
import com.kardusinfo.dicodingfootballbaru.database.myDB
import com.kardusinfo.dicodingfootballbaru.database.database
import com.kardusinfo.dicodingfootballbaru.info.InfoActivity
import kotlinx.android.synthetic.main.frag_fav.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritesFragment : Fragment() {
    private var TAG = "FavoriteFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_fav, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeFavorite.setColorSchemeResources(colorAccent,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

        swipeFavorite.setOnRefreshListener {
            showFavoriteList()
        }
        showFavoriteList()
    }

    private fun showFavoriteList() {
        swipeFavorite.isRefreshing = true
        activity?.database?.use {
            val result = select(myDB.TABLE_FAVORITE)
            val mList = result.parseList(classParser<myDB>())
            if (mList.isNotEmpty()) {
                tvFavoriteNull.visibility = View.GONE
                recyclerViewFav.layoutManager = LinearLayoutManager(activity)
                recyclerViewFav.visibility = View.VISIBLE
                recyclerViewFav.adapter = FavoritesAdapter(activity, mList) {
                    val i = Intent(activity, InfoActivity::class.java)
                    i.putExtra("event_id", it.event_id)
                    startActivity(i)
                }
            } else {
                recyclerViewFav.visibility = View.GONE
                tvFavoriteNull.visibility = View.VISIBLE
                Log.d(TAG, "mList is empty")
            }
        }
        swipeFavorite.isRefreshing = false
    }
}
