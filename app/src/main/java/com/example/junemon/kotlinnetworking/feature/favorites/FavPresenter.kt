package com.example.junemon.kotlinnetworking.feature.favorites

import android.content.Context
import android.view.View
import com.example.junemon.kotlinnetworking.base.BaseFragmentPresenter
import com.example.junemon.kotlinnetworking.databases.DatabaseModel
import com.example.junemon.kotlinnetworking.databases.database
import com.example.junemon.kotlinnetworking.helper.EspressoIdlingResource
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavPresenter(var mViews: FavView) : BaseFragmentPresenter {


    private var ctx: Context? = null

    override fun onAttach(context: Context?) {
        this.ctx = context
    }

    override fun onCreateView(view: View) {
        mViews.initView(view)

    }

    fun showFav() {
        EspressoIdlingResource.increments()
        ctx?.database?.use {
            val result = select(DatabaseModel.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<DatabaseModel>())
            mViews.showData(favorite)
            EspressoIdlingResource.decrements()
        }

    }


}