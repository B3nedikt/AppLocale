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
    var isInitial = true

    /**
     * A [AppLocaleRepository] to persist the [supportedLocales], the [currentLocale] and
     * the [desiredLocale].
     */
    @JvmStatic
    var appLocaleRepository: AppLocaleRepository? = null

    /**
     * The [localeMatchingStrategy] used to determine the new [currentLocale] after a change
     * to the [desiredLocale].
     */
    @JvmStatic
    var localeMatchingStrategy: LocaleMatchingStrategy = DefaultLocaleMatchingStrategy()

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
     *
     * Setting a new [desiredLocale] will lead to a new [currentLocale] as defined by the
     * [localeMatchingStrategy].
     */
    @JvmStatic
    var desiredLocale: Locale =
            appLocaleRepository?.desiredLocale
                    ?: Locale.getDefault()
        set(value) {
            val oldLocale = currentLocale

            field = value
            isInitial = false
            currentLocale = localeMatchingStrategy.determineNewCurrentLocale()

            if (currentLocale != oldLocale) {
                listeners.forEach(LocaleChangedListener::onLocaleChanged)
            }

            appLocaleRepository?.desiredLocale = value
        }

    private val listeners = mutableSetOf<LocaleChangedListener>()

    /**
     * Add a listener for changes to the [currentLocale]
     */
    @JvmStatic
    fun addLocaleChangedListener(listener: LocaleChangedListener) {
        listeners.add(listener)
    }

    /**
     * Remove a listener for changes to the [currentLocale]
     */
    @JvmStatic
    fun removeLocaleChangedListener(listener: LocaleChangedListener) {
        listeners.add(listener)
    }

    /**
     * Needed to wrap the original activity context.
     */
    @JvmStatic
    fun wrap(baseContext: Context): ContextWrapper = AppLocaleContextWrapper(baseContext)
}