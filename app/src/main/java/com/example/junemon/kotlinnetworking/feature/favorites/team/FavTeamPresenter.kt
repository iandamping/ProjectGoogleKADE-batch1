package com.example.junemon.kotlinnetworking.feature.favorites.team

import android.content.Context
import android.view.View
import com.example.junemon.kotlinnetworking.base.BaseFragmentPresenter
import com.example.junemon.kotlinnetworking.databases.DatabasesTeamModel
import com.example.junemon.kotlinnetworking.databases.database
import com.example.junemon.kotlinnetworking.helper.EspressoIdlingResource
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavTeamPresenter(var mView: FavTeamView) : BaseFragmentPresenter {

    private var ctx: Context? = null
    override fun onAttach(context: Context?) {
        this.ctx = context
    }

    override fun onCreateView(view: View) {
        mView.initView(view)
    }

    fun showFav() {
        EspressoIdlingResource.increments()
        ctx?.database?.use {
            val result = select(DatabasesTeamModel.TABLE_TEAM)
            val favorite = result.parseList(classParser<DatabasesTeamModel>())
            mView.onSuccesGetData(favorite)
            EspressoIdlingResource.decrements()
        }

    }
}