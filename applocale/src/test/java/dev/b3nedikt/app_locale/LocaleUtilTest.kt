package dev.b3nedikt.app_locale

import android.os.Build
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.JELLY_BEAN, Build.VERSION_CODES.P])
class LocaleUtilTest {

    @Test
    fun languageTag_onlyLanguageSet() {
        // Given a language tag with only a language
        val languageTag = "fr"

        // When we turn it into a locale and back into a language tag again
        val locale = LocaleUtil.fromLanguageTag(languageTag)
        val newLanguageTag = LocaleUtil.toLanguageTag(locale)

        // Then we should end up with the same tag
        languageTag shouldBeEqualTo newLanguageTag
    }

    @Test
    fun languageTag_languageAndCountrySet() {
        // Given a language tag with a language and country
        val languageTag = "fr-FR"

        // When we turn it into a locale and back into a language tag again
        val locale = LocaleUtil.fromLanguageTag(languageTag)
        val newLanguageTag = LocaleUtil.toLanguageTag(locale)

        // Then we should end up with the same tag
        newLanguageTag shouldBeEqualTo languageTag
    }
}