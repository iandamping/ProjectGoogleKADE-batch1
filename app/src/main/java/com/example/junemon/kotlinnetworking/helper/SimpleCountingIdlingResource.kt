package com.example.junemon.kotlinnetworking.helper

import android.support.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicInteger

class SimpleCountingIdlingResource(var resName: String) : IdlingResource {
    var mResourceName: String = resName
    val counter: AtomicInteger? = AtomicInteger(0)
    @Volatile
    var resourceCallback: IdlingResource.ResourceCallback? = null


    override fun getName(): String {
        return mResourceName
    }

    override fun isIdleNow(): Boolean {
        return counter?.get() == 0
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }

    fun increment() {
        counter?.getAndIncrement()
    }

    fun decrement() {
        var counterVal: Int? = counter?.decrementAndGet()
        if (counterVal == 0) {
            if (null != resourceCallback) {
                resourceCallback?.onTransitionToIdle()
            }
        }
        if (counterVal != null) {
            if (counterVal < 0) {
                throw IllegalArgumentException("Counter corrupted")
            }
        }
    }
}