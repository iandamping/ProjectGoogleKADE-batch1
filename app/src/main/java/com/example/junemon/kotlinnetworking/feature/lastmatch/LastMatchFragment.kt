package com.example.junemon.kotlinnetworking.feature.lastmatch

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.databases.DatabaseLeagueModel
import com.example.junemon.kotlinnetworking.feature.lastmatch.detail.DetailLastMatchActivity
import com.example.junemon.kotlinnetworking.model.MainModelLastMatch
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_lastmatch.*
import kotlinx.android.synthetic.main.activity_lastmatch.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.intentFor

class LastMatchFragment : Fragment(), LastMatchFragmentView {

    lateinit var leagueCategory: ArrayAdapter<String>
    var parseData: ArrayList<String> = ArrayList()
    var allDatas: ArrayList<DatabaseLeagueModel> = ArrayList()
    var presenter: LastMatchFragmentPresenter = LastMatchFragmentPresenter(this)
    var ctx: Context? = null
    var position: Int = 0
    var btn: Button? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.ctx = context
        presenter.onAttach(ctx)
        btn = view?.find(R.id.btnGetTeam)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var views: View = inflater.inflate(R.layout.activity_lastmatch, container, false)
        presenter.onCreateView(views)
        views.swipeLast.setOnRefreshListener {
            presenter.getData()
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
        presenter.getData()
        presenter.getAllData()

        btn?.setOnClickListener {
            presenter.getData(allDatas.get(position).idLeague)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leagueCategory = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, parseData)
        leagueCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spLastLeague.adapter = leagueCategory

        spLastLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                leagueCategory.getItem(0).toString()

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                leagueCategory.getItem(p2).toString()
                Observable.just(p2).subscribe({ data -> position })
            }

        }
    }

    override fun allLeagueData(allData: List<DatabaseLeagueModel>) {
        Observable.just(allData).subscribe({ results -> allDatas })

        Observable.fromIterable(allData).subscribe({ results -> parseData.add(results.strLeague.toString()) })
    }


}