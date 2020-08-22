package dev.b3nedikt.app_locale

import android.annotation.TargetApi
import android.content.Context
import android.content.res.*
import android.graphics.Movie
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.annotation.RequiresApi
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream


/**
 * Wrapped [Resources] which will be provided by [AppLocale].
 */
@Suppress("DEPRECATION")
internal class AppLocaleResources(
        private var baseResources: Resources,
        private val context: Context
) : Resources(baseResources.assets, baseResources.displayMetrics, baseResources.configuration) {

    @Throws(NotFoundException::class)
    override fun getString(id: Int): String {
        setLocale()

        return baseResources.getString(id)
    }

    @Throws(NotFoundException::class)
    override fun getString(id: Int, vararg formatArgs: Any): String {
        setLocale()

        return baseResources.getString(id, *formatArgs)
    }

    @Throws(NotFoundException::class)
    override fun getText(id: Int): CharSequence {
        setLocale()

        return baseResources.getText(id)
    }

    override fun getText(id: Int, def: CharSequence): CharSequence {
        setLocale()

        return baseResources.getText(id, def)
    }

    override fun getQuantityText(id: Int, quantity: Int): CharSequence {
        setLocale()

        return baseResources.getQuantityText(id, quantity)
    }

    override fun getQuantityString(id: Int, quantity: Int): String {
        setLocale()

        return baseResources.getQuantityString(id, quantity)
    }

    override fun getQuantityString(id: Int, quantity: Int, vararg formatArgs: Any?): String {
        setLocale()

        return baseResources.getQuantityString(id, quantity, *formatArgs)
    }

    override fun getStringArray(id: Int): Array<String> {
        setLocale()

        return baseResources.getStringArray(id)
    }

    override fun getTextArray(id: Int): Array<CharSequence> {
        setLocale()

        return baseResources.getTextArray(id)
    }

    @Throws(NotFoundException::class)
    override fun getAnimation(id: Int): XmlResourceParser = baseResources.getAnimation(id)

    override fun getDisplayMetrics(): DisplayMetrics = baseResources.displayMetrics

    override fun getDrawableForDensity(id: Int, density: Int): Drawable? =
            baseResources.getDrawableForDensity(id, density)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getDrawableForDensity(id: Int, density: Int, theme: Theme?): Drawable? =
            baseResources.getDrawableForDensity(id, density, theme)

    override fun getConfiguration(): Configuration = baseResources.configuration

    override fun obtainAttributes(set: AttributeSet?, attrs: IntArray?): TypedArray {
        return baseResources.obtainAttributes(set, attrs)
    }

    @Throws(NotFoundException::class)
    override fun getDimensionPixelSize(id: Int): Int = baseResources.getDimensionPixelSize(id)

    @Throws(NotFoundException::class)
    override fun getIntArray(id: Int): IntArray = baseResources.getIntArray(id)

    @Throws(NotFoundException::class)
    override fun getValue(id: Int, outValue: TypedValue?, resolveRefs: Boolean) {
        baseResources.getValue(id, outValue, resolveRefs)
    }

    @Throws(NotFoundException::class)
    override fun getValue(name: String?, outValue: TypedValue?, resolveRefs: Boolean) {
        baseResources.getValue(name, outValue, resolveRefs)
    }

    @Throws(NotFoundException::class)
    override fun getResourcePackageName(resid: Int): String =
            baseResources.getResourcePackageName(resid)

    @Throws(NotFoundException::class)
    override fun openRawResourceFd(id: Int): AssetFileDescriptor =
            baseResources.openRawResourceFd(id)

    @Throws(NotFoundException::class)
    override fun getDimension(id: Int): Float = baseResources.getDimension(id)

    @Throws(NotFoundException::class)
    override fun getColorStateList(id: Int): ColorStateList = baseResources.getColorStateList(id)

    @TargetApi(Build.VERSION_CODES.M)
    @Throws(NotFoundException::class)
    override fun getColorStateList(id: Int, theme: Theme?): ColorStateList =
            baseResources.getColorStateList(id, theme)

    @Throws(NotFoundException::class)
    override fun getBoolean(id: Int): Boolean = baseResources.getBoolean(id)

    override fun getIdentifier(name: String?, defType: String?, defPackage: String?): Int =
            baseResources.getIdentifier(name, defType, defPackage)

    @Throws(NotFoundException::class)
    override fun getColor(id: Int): Int = baseResources.getColor(id)

    @TargetApi(Build.VERSION_CODES.M)
    @Throws(NotFoundException::class)
    override fun getColor(id: Int, theme: Theme?): Int = baseResources.getColor(id, theme)

    override fun openRawResource(id: Int): InputStream = baseResources.openRawResource(id)

    @Throws(NotFoundException::class)
    override fun openRawResource(id: Int, value: TypedValue?): InputStream =
            baseResources.openRawResource(id, value)

    @Throws(NotFoundException::class)
    override fun getMovie(id: Int): Movie = baseResources.getMovie(id)

    @Throws(NotFoundException::class)
    override fun getInteger(id: Int): Int = baseResources.getInteger(id)

    @Throws(XmlPullParserException::class, IOException::class)
    override fun parseBundleExtras(parser: XmlResourceParser?, outBundle: Bundle?) {
        this.baseResources.parseBundleExtras(parser, outBundle)
    }

    @Throws(NotFoundException::class)
    override fun getDrawable(id: Int): Drawable = baseResources.getDrawable(id)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Throws(NotFoundException::class)
    override fun getDrawable(id: Int, theme: Theme?): Drawable =
            baseResources.getDrawable(id, theme)

    @Throws(NotFoundException::class)
    override fun getResourceTypeName(resid: Int): String = baseResources.getResourceTypeName(resid)

    @Throws(NotFoundException::class)
    override fun getLayout(id: Int): XmlResourceParser = baseResources.getLayout(id)

    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(NotFoundException::class)
    override fun getFont(id: Int): Typeface = baseResources.getFont(id)

    @Throws(NotFoundException::class)
    override fun getXml(id: Int): XmlResourceParser = baseResources.getXml(id)

    @Throws(NotFoundException::class)
    override fun getResourceName(resid: Int): String = baseResources.getResourceName(resid)

    @Throws(XmlPullParserException::class)
    override fun parseBundleExtra(tagName: String?, attrs: AttributeSet?, outBundle: Bundle?) {
        baseResources.parseBundleExtra(tagName, attrs, outBundle)
    }

    @Throws(NotFoundException::class)
    override fun getDimensionPixelOffset(id: Int): Int = baseResources.getDimensionPixelOffset(id)

    @Throws(NotFoundException::class)
    override fun getValueForDensity(
            id: Int,
            density: Int,
            outValue: TypedValue?,
            resolveRefs: Boolean
    ) {
        baseResources.getValueForDensity(id, density, outValue, resolveRefs)
    }

    @Throws(NotFoundException::class)
    override fun getResourceEntryName(resid: Int): String =
            baseResources.getResourceEntryName(resid)

    @Throws(NotFoundException::class)
    override fun getFraction(id: Int, base: Int, pbase: Int): Float =
            baseResources.getFraction(id, base, pbase)

    private fun setLocale() {
        if (AppLocale.isInitial) return

        val conf = baseResources.configuration

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(AppLocale.currentLocale)
            baseResources = context.createConfigurationContext(conf).resources
        } else {
            conf.locale = AppLocale.currentLocale
            baseResources.updateConfiguration(conf, baseResources.displayMetrics)
        }
    }
}

