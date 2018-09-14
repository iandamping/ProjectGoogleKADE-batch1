package com.example.junemon.kotlinnetworking.feature.nextmatch

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.feature.lastmatch.LastMatchFragment
import com.example.junemon.kotlinnetworking.feature.nextmatch.detail.DetailNextMatchActivity
import com.example.junemon.kotlinnetworking.model.MainModelNextMatch
import kotlinx.android.synthetic.main.activity_nextmatch.*
import kotlinx.android.synthetic.main.activity_nextmatch.view.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.yesButton

class NextMatchFragment : Fragment(), NextMatchFragmentView {


    var ctx: Context? = null


    var presenter: NextMatchFragmentPresenter = NextMatchFragmentPresenter(this)

    var TYPE_NEWS: String? = "type_league"
    lateinit var typeNews: String


    fun newInstance(type: String?): NextMatchFragment {
        val bundle = Bundle()
        val fragment = NextMatchFragment()
        bundle.putString(TYPE_NEWS, type)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        typeNews = args?.getString(TYPE_NEWS) ?: "4328"
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.ctx = context
        presenter.onAttach(ctx)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var views: View = inflater.inflate(R.layout.activity_nextmatch, container, false)
        views.swipeNext.setOnRefreshListener {
            presenter.getData(typeNews)
            swipeNext.isRefreshing = false
        }
        presenter.onCreateView(views)
        return views
    }

    override fun onSuccess(data: List<MainModelNextMatch.Event>) {
        rvFootbalEventNextMatch.layoutManager = LinearLayoutManager(ctx)

        rvFootbalEventNextMatch.adapter = NextMatchAdapter(ctx, data) {
            startActivity(intentFor<DetailNextMatchActivity>(Integer.toString(R.string.parcel_key) to it))
        }
        rvFootbalEventNextMatch.adapter.notifyDataSetChanged()
    }

    override fun onFailed(message: String) {
        alert(message) { yesButton { "Oke" } }.show()
    }

    override fun initView(view: View) {
        presenter.getData(typeNews)

    }


}