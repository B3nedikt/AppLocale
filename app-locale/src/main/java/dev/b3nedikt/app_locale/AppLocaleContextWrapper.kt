package dev.b3nedikt.app_locale

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources

/**
 * [ContextWrapper] for [AppLocale] which wraps the context for providing custom [Resources].
 */
internal class AppLocaleContextWrapper(base: Context) : ContextWrapper(base) {

    private val res: Resources by lazy {
        val baseResources = super.getResources()
        AppLocaleResources(baseResources)
    }

    override fun getResources() = res
}