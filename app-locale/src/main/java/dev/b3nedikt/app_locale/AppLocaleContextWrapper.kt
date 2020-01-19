package dev.b3nedikt.app_locale

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources

/**
 * Main Restring context wrapper which wraps the context for providing another layout inflater
 * & resources.
 */
internal class AppLocaleContextWrapper constructor(
        base: Context
) : ContextWrapper(base) {

    private val res: Resources by lazy {
        val baseResources = super.getResources()
        AppLocaleResources(baseResources)
    }

    override fun getResources() = res

    companion object {

        @JvmStatic
        fun wrap(context: Context): AppLocaleContextWrapper {
            return AppLocaleContextWrapper(context)
        }
    }
}