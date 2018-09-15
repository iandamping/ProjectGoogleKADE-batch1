package com.example.junemon.kotlinnetworking.feature.favorites

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.junemon.kotlinnetworking.feature.favorites.matches.FavFragment
import com.example.junemon.kotlinnetworking.feature.favorites.team.FavTeamFrag

class FavoriteAdapterFragment(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val page_count: Int = 2
    private val tabTitle = arrayOf("Matches", "Teams")

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = FavFragment()
        when (position) {
            1 -> fragment = FavTeamFrag()
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