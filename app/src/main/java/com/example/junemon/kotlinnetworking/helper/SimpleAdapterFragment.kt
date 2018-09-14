package com.example.junemon.kotlinnetworking.helper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.junemon.kotlinnetworking.feature.lastmatch.LastMatchFragment
import com.example.junemon.kotlinnetworking.feature.nextmatch.NextMatchFragment

class SimpleAdapterFragment(fragmentManager: FragmentManager, var data:String) : FragmentPagerAdapter(fragmentManager) {
    private val page_count: Int = 2
    private val tabTitle = arrayOf("Last Match", "Next Match")

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = LastMatchFragment().newInstance(data)
        when (position) {
            1 -> fragment = NextMatchFragment().newInstance(data)
        }
        return fragment
    }

    override fun getCount(): Int {
        return page_count
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitle[position]
    }
}