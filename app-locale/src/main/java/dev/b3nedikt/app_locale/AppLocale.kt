package dev.b3nedikt.app_locale

import android.content.Context
import android.content.ContextWrapper
import java.util.*


/**
 * AppLocale is a android library to update the app language dynamically.
 */
object AppLocale {

    /**
     * If the [desiredLocale] has not been changed yet.
     */
    @JvmStatic
    var isInitial = false

    /**
     * The [Locale]s supported by the app. Usually these are all [Locale]s for which
     * strings.xml files exist.
     */
    @JvmStatic
    var supportedLocales: List<Locale> = listOf()

    /**
     * The locale currently used by the app.
     */
    @JvmStatic
    var currentLocale: Locale = Locale.getDefault()

    /**
     * The locale which should ideally be used by the app.
     * If the locale is in the [supportedLocales], it will be used, otherwise we will use
     * the [Locale] in the [supportedLocales] which is closest to our [desiredLocale].
     * To determine the closest [Locale] we first search for [Locale]s with the same language
     * and variant. If none exists, we take the first one from the [supportedLocales] with the
     * same language. If no locale with the same language exists we just take the first of the
     * [supportedLocales].
     *
     * If no [supportedLocales] are defined we just assume the app supports every [Locale].
     */
    @JvmStatic
    var desiredLocale: Locale = Locale.getDefault()
        set(value) {
            field = value
            isInitial = false
            currentLocale = getLocale()
        }

    /**
     * Needed to wrap the original activity context.
     */
    @JvmStatic
    fun wrap(baseContext: Context): ContextWrapper = AppLocaleContextWrapper(baseContext)

    private fun getLocale() =
            if (supportedLocales.isEmpty()) desiredLocale else getLocaleFromSupportedLocals()

    private fun getLocaleFromSupportedLocals() =
            supportedLocales.find { it == desiredLocale }
                    ?: supportedLocales.find { sameLanguage(it) && sameVariant(it) }
                    ?: supportedLocales.find { sameLanguage(it) }
                    ?: supportedLocales.first()

    private fun sameVariant(locale: Locale) = locale.variant == desiredLocale.variant

    private fun sameLanguage(locale: Locale) = locale.language == desiredLocale.language
}