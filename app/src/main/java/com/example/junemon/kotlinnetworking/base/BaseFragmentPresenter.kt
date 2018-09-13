package com.example.junemon.kotlinnetworking.base

import android.content.Context
import android.view.View

interface BaseFragmentPresenter {
    fun onAttach(context: Context?)
    fun onCreateView(view: View)

}