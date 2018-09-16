package com.example.junemon.kotlinnetworking.feature.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.feature.lastmatch.detail.DetailLastMatchActivity
import com.example.junemon.kotlinnetworking.feature.nextmatch.detail.DetailNextMatchActivity
import com.example.junemon.kotlinnetworking.feature.team.detail.TeamDetail
import com.example.junemon.kotlinnetworking.helper.EspressoIdlingResource
import com.example.junemon.kotlinnetworking.model.MainModelLastMatch
import com.example.junemon.kotlinnetworking.model.MainModelNextMatch
import com.example.junemon.kotlinnetworking.model.TeamModel
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_search_team.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.intentFor

class SearchActivity : AppCompatActivity(), SerachView, View.OnClickListener {


    lateinit var presenter: SearchPresenter
    var matches: TeamModel.Team? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)
        presenter = SearchPresenter(this)
        presenter.onCreate(this)

    }

    override fun getLastData(data: List<MainModelLastMatch.Event>) {
        rvLastMatchSearch.layoutManager = LinearLayoutManager(this)
        if (data.isNotEmpty() && data.size > 0) {
            llNews.visibility = View.VISIBLE
            rvLastMatchSearch.adapter = SearchLastAdapter(this, data) {
                startActivity(intentFor<DetailLastMatchActivity>(Integer.toString(R.string.parcel_key) to it))
            }
            rvLastMatchSearch.setNestedScrollingEnabled(false)
        }

    }

    override fun getNextData(data: List<MainModelNextMatch.Event>) {
        rvNextMatchSearch.layoutManager = LinearLayoutManager(this)
        if (data.isNotEmpty() && data.size > 0) {
            llArticle.visibility = View.VISIBLE

            rvNextMatchSearch.adapter = SearchNextAdapter(this, data) {
                startActivity(intentFor<DetailNextMatchActivity>(Integer.toString(R.string.parcel_key) to it))
            }
            rvNextMatchSearch.setNestedScrollingEnabled(false)
        }

    }

    override fun getAllTeam(data: List<TeamModel.Team>) {
        Observable.just(data).subscribe({ results -> matches })
        rvAllTeamSearch.layoutManager = LinearLayoutManager(this)
        if (data.isNotEmpty() && data.size > 0) {
            llAllTeam.visibility = View.VISIBLE

            rvAllTeamSearch.adapter = SearchTeamsAdapter(this, data) {
                startActivity(intentFor<TeamDetail>(Integer.toString(R.string.data_all_team) to it))
            }
            rvAllTeamSearch.setNestedScrollingEnabled(false)
        }
    }


    override fun onFailed(message: String) {
        llNotFound.visibility = View.VISIBLE
        tvNotFound.text = message
    }

    override fun initView() {

        etSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_SEARCH) {
                    requestTeamDataDetails(etSearch.text.toString())
                    return true
                }
                return false
            }
        })
        ibSearch.setOnClickListener(this)
    }

    private fun requestTeamDataDetails(keyword: String) {
        presenter.getAllTeamDetails(keyword)


    }


    override fun onClick(p0: View?) {
        if (p0 === ibSearch) {
            if (!etSearch.text.toString().trim { it <= ' ' }.equals("", ignoreCase = true)) {
                requestTeamDataDetails(etSearch.text.toString())
            }
        }
    }
}