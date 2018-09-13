package com.example.junemon.kotlinnetworking.helper

import android.support.test.espresso.IdlingResource


class EspressoIdlingResource {

    companion object {
        val RESOURCE: String = "Global"
        var mCountingIdlingResource: SimpleCountingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

        fun increments() {
            mCountingIdlingResource.increment()
        }

        fun decrements() {
            mCountingIdlingResource.decrement()
        }

        fun getIdlingRes(): IdlingResource {
            return mCountingIdlingResource
        }
    }
}