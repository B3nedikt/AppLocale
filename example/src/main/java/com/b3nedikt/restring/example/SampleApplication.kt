package com.b3nedikt.restring.example

import android.app.Application
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.app_locale.SharedPrefsAppLocaleRepository
import dev.b3nedikt.reword.RewordInterceptor
import io.github.inflationx.viewpump.ViewPump
import java.util.*


class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppLocale.supportedLocales = listOf(Locale.ENGLISH, Locale.US, LOCALE_AUSTRIAN_GERMAN)

        // Optional: Persist changes to the desiredLocale to sharedPreferences
        AppLocale.appLocaleRepository = SharedPrefsAppLocaleRepository(this)

        ViewPump.init(ViewPump.builder()
                .addInterceptor(RewordInterceptor)
                .build()
        )
    }

    private companion object {
        val LOCALE_AUSTRIAN_GERMAN = Locale("de", "AT")
    }
}