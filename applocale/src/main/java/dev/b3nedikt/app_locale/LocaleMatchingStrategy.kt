package dev.b3nedikt.app_locale

import java.util.*

/**
 * Defines how [AppLocale] finds the new [AppLocale.currentLocale] from a set of
 * [AppLocale.supportedLocales] given a new [AppLocale.desiredLocale].
 */
interface LocaleMatchingStrategy {

    /**
     * Determines the new [AppLocale.currentLocale]
     * @return the new [AppLocale.currentLocale]
     */
    fun determineNewCurrentLocale(): Locale
}