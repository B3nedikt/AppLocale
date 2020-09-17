package dev.b3nedikt.app_locale

import android.content.Context
import android.content.res.Resources
import android.os.Build

internal class ResourcesDelegate(
        private val context: Context,
        private val baseResources: Resources
) {

    private var res = baseResources

    fun getString(id: Int): String {
        setLocale()

        return res.getString(id)
    }

    fun getString(id: Int, vararg formatArgs: Any): String {
        setLocale()

        return res.getString(id, *formatArgs)
    }

    fun getText(id: Int): CharSequence {
        setLocale()

        return res.getText(id)
    }

    fun getText(id: Int, def: CharSequence): CharSequence {
        setLocale()

        return res.getText(id, def)
    }

    fun getQuantityText(id: Int, quantity: Int): CharSequence {
        setLocale()

        return res.getQuantityText(id, quantity)
    }

    fun getQuantityString(id: Int, quantity: Int): String {
        setLocale()

        return res.getQuantityString(id, quantity)
    }

    fun getQuantityString(id: Int, quantity: Int, vararg formatArgs: Any?): String {
        setLocale()

        return res.getQuantityString(id, quantity, *formatArgs)
    }

    fun getStringArray(id: Int): Array<String> {
        setLocale()

        return res.getStringArray(id)
    }

    fun getTextArray(id: Int): Array<CharSequence> {
        setLocale()

        return res.getTextArray(id)
    }

    @Suppress("DEPRECATION")
    private fun setLocale() {
        if (AppLocale.isInitial) return

        val conf = baseResources.configuration

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(AppLocale.currentLocale)
            res = context.createConfigurationContext(conf).resources
        } else {
            conf.locale = AppLocale.currentLocale
            res.updateConfiguration(conf, baseResources.displayMetrics)
        }
    }
}