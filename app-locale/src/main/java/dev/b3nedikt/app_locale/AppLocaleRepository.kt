package dev.b3nedikt.app_locale

import java.util.*

/**
 * Repository for [AppLocale]
 */
interface AppLocaleRepository {

    /**
     * The locale which should ideally be used by the app, or null if the [Locale.getDefault]
     * should be used.
     */
    var desiredLocale: Locale?
}