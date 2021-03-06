package com.example.junemon.kotlinnetworking.feature.favorites.matches

import com.example.junemon.kotlinnetworking.base.BaseFragmentView
import com.example.junemon.kotlinnetworking.databases.DatabaseModel

interface FavView : BaseFragmentView {
    fun showData(listDta: List<DatabaseModel>)
}