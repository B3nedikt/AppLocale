package dev.b3nedikt.app_locale

import java.util.*

/**
 * Repository for [AppLocale]
 */
interface AppLocaleRepository {

    /**
     * The locale which should ideally be used by the app.
     */
    var desiredLocale: Locale?
}