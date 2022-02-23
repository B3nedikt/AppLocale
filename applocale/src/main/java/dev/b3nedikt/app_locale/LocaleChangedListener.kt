package dev.b3nedikt.app_locale

/**
 * Listener for changes to the [AppLocale.currentLocale]
 */
fun interface LocaleChangedListener {

    /**
     * Called when the [AppLocale.currentLocale] has changed
     */
    fun onLocaleChanged()
}