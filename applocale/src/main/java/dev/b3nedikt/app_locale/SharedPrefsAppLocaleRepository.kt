package dev.b3nedikt.app_locale

import android.content.Context
import android.content.SharedPreferences
import java.util.*

/**
 * Implementation of [AppLocaleRepository] which saves the [AppLocale.desiredLocale] to
 * the shared preferences.
 */
class SharedPrefsAppLocaleRepository(private val context: Context) : AppLocaleRepository {

    private val sharedPrefs: SharedPreferences by lazy {
        context.getSharedPreferences(APP_LOCALE_SHARED_PREFS, Context.MODE_PRIVATE)
    }

    override var desiredLocale: Locale?
        get() = loadLocale()
        set(value) =
            if (value != null) saveLocale(value) else resetLocale()

    private fun saveLocale(locale: Locale) =
            sharedPrefs
                    .edit()
                    .putString(LANGUAGE_TAG_SHARED_PREFS_KEY, LocaleUtil.toLanguageTag(locale))
                    .apply()

    private fun loadLocale(): Locale? =
            sharedPrefs
                    .getString(LANGUAGE_TAG_SHARED_PREFS_KEY, null)
                    ?.let { LocaleUtil.fromLanguageTag(it) }

    private fun resetLocale() =
            sharedPrefs
                    .edit()
                    .remove(LANGUAGE_TAG_SHARED_PREFS_KEY)
                    .apply()

    private companion object {
        private const val APP_LOCALE_SHARED_PREFS = "dev.b3nedikt.app_locale.LanguageTag"
        private const val LANGUAGE_TAG_SHARED_PREFS_KEY = "LanguageTag"
    }
}