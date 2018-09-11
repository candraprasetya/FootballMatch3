package com.kardusinfo.dicodingfootballbaru


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.kardusinfo.dicodingfootballbaru.R.id.*
import com.kardusinfo.dicodingfootballbaru.favorites.FavoritesFragment
import com.kardusinfo.dicodingfootballbaru.next.NextFragment
import com.kardusinfo.dicodingfootballbaru.prev.PreviousFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

    }

        inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> PreviousFragment()
                    1 -> NextFragment()
                    2 -> FavoritesFragment()
                    else -> Fragment()
                }
            }

            override fun getCount(): Int {
                return 3
            }
        }
    }
