package dev.b3nedikt.app_locale

import org.amshove.kluent.shouldBe
import org.junit.Test
import java.util.*

class AppLocaleTest {

    @Test()
    fun desiredLocale_InSupportedLocales() {

        // Given a list of supported locales
        AppLocale.supportedLocales = listOf(Locale.GERMAN)

        // When we set the desired locale to German
        AppLocale.desiredLocale = Locale.GERMAN

        // Then the currentLocale should also be German
        AppLocale.currentLocale shouldBe Locale.GERMAN
    }

    @Test()
    fun desiredLocale_NotInSupportedLocales() {

        // Given a list of supported locales
        AppLocale.supportedLocales = listOf(Locale.ENGLISH)

        // When we set the desired locale to German
        AppLocale.desiredLocale = Locale.GERMAN

        // Then the currentLocale should be English
        AppLocale.currentLocale shouldBe Locale.ENGLISH
    }

    @Test()
    fun desiredLocale_EmptySupportedLocales() {

        // Given a list of supported locales
        AppLocale.supportedLocales = listOf()

        // When we set the desired locale to German
        AppLocale.desiredLocale = Locale.GERMAN

        // Then we assume the user does not want to constrain the languages available by the app
        AppLocale.currentLocale shouldBe Locale.GERMAN
    }
}