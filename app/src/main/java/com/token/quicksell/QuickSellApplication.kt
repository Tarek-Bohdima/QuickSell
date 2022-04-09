package com.token.quicksell

import android.app.Application
import timber.log.Timber

class QuickSellApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}