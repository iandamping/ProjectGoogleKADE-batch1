package com.example.junemon.kotlinnetworking

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.helper.SimpleAdapterFragment

class MainPager : Fragment() {
    lateinit var vpMainPager: ViewPager
    lateinit var tabMainPager: TabLayout
    var TYPE_NEWS: String? = "type_league"
    lateinit var type: String

    fun newInstance(type: String?): MainPager {
        val bundle = Bundle()
        val fragment = MainPager()
        bundle.putString(TYPE_NEWS, type)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        type = args?.getString(TYPE_NEWS) ?: "4328"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var views: View = inflater.inflate(R.layout.main_pager, container, false)
        vpMainPager = views.findViewById(R.id.vpMainPage) as ViewPager
        tabMainPager = views.findViewById(R.id.tabMainPage) as TabLayout
        tabMainPager.setupWithViewPager(vpMainPager)
        vpMainPager.adapter = buildAdapter()
        return views

    }


    fun buildAdapter(): PagerAdapter {
        return SimpleAdapterFragment(childFragmentManager, type)
    }


}