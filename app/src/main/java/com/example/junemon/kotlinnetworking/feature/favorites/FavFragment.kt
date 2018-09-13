package com.example.junemon.kotlinnetworking.feature.favorites

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R
import com.example.junemon.kotlinnetworking.databases.DatabaseModel
import com.example.junemon.kotlinnetworking.feature.favorites.detail.FavDetailActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_favorite.view.*
import org.jetbrains.anko.support.v4.intentFor

class FavFragment : Fragment(), FavView {
    lateinit var viewsd: View
    var presenter: FavPresenter = FavPresenter(this)
    var ctx: Context? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.ctx = context
        presenter.onAttach(ctx)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var views: View = inflater.inflate(R.layout.activity_favorite, container, false)
        presenter.onCreateView(views)
        views.swipesRefreshs.setOnRefreshListener {
            presenter.showFav()
            swipesRefreshs.isRefreshing = false
        }
        return views
    }

    override fun showData(listDta: List<DatabaseModel>) {
        viewsd.rvFootbalEventFav.layoutManager = LinearLayoutManager(ctx)

        viewsd.rvFootbalEventFav.adapter = FavAdapter(ctx, listDta) {
            startActivity(intentFor<FavDetailActivity>(Integer.toString(R.string.parcel_data_key) to it))
        }

        viewsd.rvFootbalEventFav.adapter.notifyDataSetChanged()
    }

    override fun initView(view: View) {
        viewsd = view
        presenter.showFav()

    }

}