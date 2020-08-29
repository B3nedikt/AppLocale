package dev.b3nedikt.app_locale

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.amshove.kluent.shouldBe
import org.junit.Test
import java.util.*

class AppLocaleTest {

    @Test
    fun desiredLocale_InSupportedLocales() {

        // Given a list of supported locales
        AppLocale.supportedLocales = listOf(Locale.GERMAN)

        // When we set the desired locale to German
        AppLocale.desiredLocale = Locale.GERMAN

        // Then the currentLocale should also be German
        AppLocale.currentLocale shouldBe Locale.GERMAN
    }

    @Test
    fun desiredLocale_NotInSupportedLocales() {

        // Given a list of supported locales
        AppLocale.supportedLocales = listOf(Locale.ENGLISH)

        // When we set the desired locale to German
        AppLocale.desiredLocale = Locale.GERMAN

        // Then the currentLocale should be English
        AppLocale.currentLocale shouldBe Locale.ENGLISH
    }

    @Test
    fun desiredLocale_EmptySupportedLocales() {

        // Given a list of supported locales
        AppLocale.supportedLocales = listOf()

        // When we set the desired locale to German
        AppLocale.desiredLocale = Locale.GERMAN

        // Then we assume the user does not want to constrain the languages available by the app
        AppLocale.currentLocale shouldBe Locale.GERMAN
    }

    @Test
    fun successfulLanguageChange_TriggersListener() {

        // Given the AppLocale is set to English
        AppLocale.desiredLocale = Locale.ENGLISH

        val listener: LocaleChangedListener = mock()
        AppLocale.addLocaleChangedListener(listener)

        // When we add a listener & set the desired locale to German
        AppLocale.desiredLocale = Locale.GERMAN

        // Then the listener should be triggered
        verify(listener).onLocaleChanged()
    }

    @Test
    fun failedLanguageChange_DoesNotTriggersListener() {

        // Given the AppLocale is set to English
        AppLocale.desiredLocale = Locale.ENGLISH

        // When we add a listener & set the desired locale to English again
        val listener: LocaleChangedListener = mock()
        AppLocale.addLocaleChangedListener(listener)
        AppLocale.desiredLocale = Locale.GERMAN

        // Then the listener should not be triggered
        verify(listener).onLocaleChanged()
    }

    @Test
    fun afterRemoveListener_ListenerNoLongerGetsTriggered() {

        // Given the AppLocale is set to English
        AppLocale.desiredLocale = Locale.ENGLISH

        // When we add a listener, removed it and then set the desired locale to English again
        val listener: LocaleChangedListener = mock()
        AppLocale.addLocaleChangedListener(listener)
        AppLocale.removeLocaleChangedListener(listener)
        AppLocale.desiredLocale = Locale.GERMAN

        // Then the listener should not be triggered
        verify(listener).onLocaleChanged()
    }
}

