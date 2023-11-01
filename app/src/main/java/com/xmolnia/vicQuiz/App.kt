package com.xmolnia.vicQuiz

import android.app.Application
import android.content.Context

/**
 * Created by Osman Boy on 18.03.2021.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appClass = this
        MySharePreference.getInstance(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base))
    }

    companion object {
        private var appClass: App? = null
        @JvmStatic
        fun getintance(): App? {
            return appClass
        }
    }
}