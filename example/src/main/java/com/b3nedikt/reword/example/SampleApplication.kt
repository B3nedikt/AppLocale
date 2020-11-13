package com.b3nedikt.reword.example

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.app_locale.SharedPrefsAppLocaleRepository
import dev.b3nedikt.reword.RewordInterceptor
import dev.b3nedikt.viewpump.ViewPump
import java.util.*


class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppLocale.supportedLocales = listOf(Locale.ENGLISH, Locale.US, LOCALE_AUSTRIAN_GERMAN)

        // Optional: Persist changes to the desiredLocale to sharedPreferences
        AppLocale.appLocaleRepository = SharedPrefsAppLocaleRepository(this)

        ViewPump.init(RewordInterceptor)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(AppLocale.wrap(newBase))
    }

    override fun getResources(): Resources {
        return AppLocale.wrap(baseContext).resources
    }

    private companion object {
        val LOCALE_AUSTRIAN_GERMAN = Locale("de", "AT")
    }
}