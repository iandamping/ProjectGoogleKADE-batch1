package com.example.junemon.kotlinnetworking.helper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.junemon.kotlinnetworking.feature.lastmatch.LastMatchFragment
import com.example.junemon.kotlinnetworking.feature.nextmatch.NextMatchFragment

class SimpleAdapterFragment(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val page_count: Int = 2
    private val tabTitle = arrayOf("Last Match", "Next Match")

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = LastMatchFragment()
        when (position) {
            1 -> fragment = NextMatchFragment()
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