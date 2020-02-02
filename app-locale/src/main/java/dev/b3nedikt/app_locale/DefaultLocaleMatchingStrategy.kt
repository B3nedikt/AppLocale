package dev.b3nedikt.app_locale

import dev.b3nedikt.app_locale.AppLocale.desiredLocale
import dev.b3nedikt.app_locale.AppLocale.supportedLocales
import java.util.*

/**
 * If the locale is in the [supportedLocales], it will be used, otherwise we will use
 * the [Locale] in the [supportedLocales] which is closest to our [desiredLocale].
 *
 * If no [supportedLocales] are defined we just assume the app supports every [Locale].
 */
internal class DefaultLocaleMatchingStrategy : LocaleMatchingStrategy {

    override fun determineNewCurrentLocale() =
            if (supportedLocales.isEmpty()) desiredLocale else getLocaleFromSupportedLocals()

    private fun getLocaleFromSupportedLocals() =
            supportedLocales.find { it == desiredLocale }
                    ?: supportedLocales.find { sameLanguage(it) && sameCountry(it) && sameVariant(it) }
                    ?: supportedLocales.find { sameLanguage(it) && sameCountry(it) }
                    ?: supportedLocales.find { sameLanguage(it) }
                    ?: supportedLocales.first()

    private fun sameVariant(locale: Locale) = locale.variant == desiredLocale.variant

    private fun sameLanguage(locale: Locale) = locale.language == desiredLocale.language

    private fun sameCountry(locale: Locale) = locale.country == desiredLocale.country
}