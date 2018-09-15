package com.example.junemon.kotlinnetworking.feature.favorites

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R

class FavPager : Fragment() {
    lateinit var vpFavPager: ViewPager
    lateinit var tabFavPager: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var views: View = inflater.inflate(R.layout.activity_fav_pager, container, false)
        vpFavPager = views.findViewById(R.id.vpFavPage) as ViewPager
        tabFavPager = views.findViewById(R.id.tabFavPage) as TabLayout
        tabFavPager.setupWithViewPager(vpFavPager)
        vpFavPager.adapter = buildAdapter()
        return views
    }


    fun buildAdapter(): PagerAdapter {
        return FavoriteAdapterFragment(childFragmentManager)
    }

}