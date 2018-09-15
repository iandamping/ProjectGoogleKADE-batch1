package com.example.junemon.kotlinnetworking.feature.lastmatch

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.feature.lastmatch.detail.DetailLastMatchActivity
import com.example.junemon.kotlinnetworking.model.MainModelLastMatch
import kotlinx.android.synthetic.main.activity_lastmatch.*
import kotlinx.android.synthetic.main.activity_lastmatch.view.*
import org.jetbrains.anko.support.v4.intentFor


class LastMatchFragment : Fragment(), LastMatchFragmentView {

    var presenter: LastMatchFragmentPresenter = LastMatchFragmentPresenter(this)
    var ctx: Context? = null
    var TYPE_NEWS: String? = "type_league"
    lateinit var typeNews: String


    fun newInstance(type: String?): LastMatchFragment {
        val bundle = Bundle()
        val fragment = LastMatchFragment()
        bundle.putString(TYPE_NEWS, type)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        typeNews = args?.getString(TYPE_NEWS) ?: "4328"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.ctx = context
        presenter.onAttach(ctx)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var views: View = inflater.inflate(R.layout.activity_lastmatch, container, false)
        presenter.onCreateView(views)

        views.swipeLast.setOnRefreshListener {
            presenter.getData(typeNews)
            swipeLast.isRefreshing = false
        }
        return views
    }

    override fun onSuccess(data: List<MainModelLastMatch.Event>) {
        rvFootbalEventLastMatch.layoutManager = LinearLayoutManager(ctx)
        rvFootbalEventLastMatch.adapter = LastMatchAdapter(ctx, data) {
            startActivity(intentFor<DetailLastMatchActivity>(Integer.toString(R.string.parcel_key) to it))
        }

        rvFootbalEventLastMatch.adapter.notifyDataSetChanged()
    }

    override fun onFailed(message: String) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
    }

    override fun initView(view: View) {
        presenter.getData(typeNews)


    }


}