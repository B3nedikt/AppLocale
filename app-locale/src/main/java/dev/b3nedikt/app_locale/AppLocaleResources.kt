package dev.b3nedikt.app_locale

import android.content.res.Resources
import android.os.Build


/**
 * Wrapped [Resources] which will be provided by AppLocale.
 */
@Suppress("DEPRECATION")
internal class AppLocaleResources(val res: Resources)
    : Resources(res.assets, res.displayMetrics, res.configuration) {

    @Throws(NotFoundException::class)
    override fun getString(id: Int): String {
        setLocale()

        return super.getString(id)
    }

    @Throws(NotFoundException::class)
    override fun getString(id: Int, vararg formatArgs: Any): String {
        setLocale()

        return super.getString(id, *formatArgs)
    }

    @Throws(NotFoundException::class)
    override fun getText(id: Int): CharSequence {
        setLocale()

        return super.getText(id)
    }

    override fun getText(id: Int, def: CharSequence): CharSequence {
        setLocale()

        return super.getText(id, def)
    }

    override fun getQuantityText(id: Int, quantity: Int): CharSequence {
        setLocale()

        return super.getQuantityText(id, quantity)
    }

    override fun getQuantityString(id: Int, quantity: Int): String {
        setLocale()

        return super.getQuantityString(id, quantity)
    }

    override fun getQuantityString(id: Int, quantity: Int, vararg formatArgs: Any?): String {
        setLocale()

        return super.getQuantityString(id, quantity, *formatArgs)
    }

    override fun getStringArray(id: Int): Array<String> {
        setLocale()

        return super.getStringArray(id)
    }

    override fun getTextArray(id: Int): Array<CharSequence> {
        setLocale()

        return super.getTextArray(id)
    }

    private fun setLocale() {
        if (AppLocale.isInitial) return

        val conf = res.configuration

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(AppLocale.currentLocale)
        } else {
            conf.locale = AppLocale.currentLocale
        }

        res.updateConfiguration(conf, res.displayMetrics)
    }
}

