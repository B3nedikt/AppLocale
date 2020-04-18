package dev.b3nedikt.app_locale

import android.content.Context
import android.content.res.Resources
import android.os.Build


/**
 * Wrapped [Resources] which will be provided by AppLocale.
 */
@Suppress("DEPRECATION")
internal class AppLocaleResources(
        private var res: Resources,
        private val context: Context
) : Resources(res.assets, res.displayMetrics, res.configuration) {

    @Throws(NotFoundException::class)
    override fun getString(id: Int): String {
        setLocale()

        return res.getString(id)
    }

    @Throws(NotFoundException::class)
    override fun getString(id: Int, vararg formatArgs: Any): String {
        setLocale()

        return res.getString(id, *formatArgs)
    }

    @Throws(NotFoundException::class)
    override fun getText(id: Int): CharSequence {
        setLocale()

        return res.getText(id)
    }

    override fun getText(id: Int, def: CharSequence): CharSequence {
        setLocale()

        return res.getText(id, def)
    }

    override fun getQuantityText(id: Int, quantity: Int): CharSequence {
        setLocale()

        return res.getQuantityText(id, quantity)
    }

    override fun getQuantityString(id: Int, quantity: Int): String {
        setLocale()

        return res.getQuantityString(id, quantity)
    }

    override fun getQuantityString(id: Int, quantity: Int, vararg formatArgs: Any?): String {
        setLocale()

        return res.getQuantityString(id, quantity, *formatArgs)
    }

    override fun getStringArray(id: Int): Array<String> {
        setLocale()

        return res.getStringArray(id)
    }

    override fun getTextArray(id: Int): Array<CharSequence> {
        setLocale()

        return res.getTextArray(id)
    }

    private fun setLocale() {
        if (AppLocale.isInitial) return

        val conf = res.configuration

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(AppLocale.currentLocale)
        } else {
            conf.locale = AppLocale.currentLocale
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            res = context.createConfigurationContext(conf).resources
        } else {
            res.updateConfiguration(conf, res.displayMetrics)
        }
    }
}

