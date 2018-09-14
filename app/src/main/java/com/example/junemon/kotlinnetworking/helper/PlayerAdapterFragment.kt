package com.example.junemon.kotlinnetworking.helper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.junemon.kotlinnetworking.feature.nextmatch.NextMatchFragment
import com.example.junemon.kotlinnetworking.feature.team.detail.overview.OverviewFrag
import com.example.junemon.kotlinnetworking.model.TeamModel

class PlayerAdapterFragment(fragmentManager: FragmentManager, var data: TeamModel.Team) : FragmentPagerAdapter(fragmentManager) {
    private val page_count: Int = 2
    private val tabTitle = arrayOf("Overview", "Player")

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = OverviewFrag().newInstance(data.idTeam)
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