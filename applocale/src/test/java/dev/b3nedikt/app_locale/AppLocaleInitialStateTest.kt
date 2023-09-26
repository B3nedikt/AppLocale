package dev.b3nedikt.app_locale

import org.amshove.kluent.shouldBe
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import java.util.*

@RunWith(RobolectricTestRunner::class)
class AppLocaleInitialStateTest {

    @Test
    fun afterSettingTheAppLocaleRepository_TheCurrentLocaleShouldUseItWhenInitialized() {

        // Given a repository with the desiredLocale set to Chinese
        val repository: AppLocaleRepository = mock {
            on { desiredLocale } doReturn Locale.CHINESE
        }

        // And we support Chinese, German & English
        AppLocale.supportedLocales = listOf(Locale.CHINESE, Locale.GERMAN, Locale.ENGLISH)

        // When we set this repository on AppLocale
        AppLocale.appLocaleRepository = repository

        // Then the currentLocale should be Chinese & isInitial false
        AppLocale.currentLocale shouldBe Locale.CHINESE
        AppLocale.isInitial shouldBe false
    }

    @Test
    fun withoutSettingTheAppLocaleRepository_TheCurrentLocaleShouldBeTheDefaultLocale() {

        // And we support Chinese, German & English
        AppLocale.supportedLocales = listOf(Locale.CHINESE, Locale.GERMAN, Locale.ENGLISH)

        // Then the currentLocale should be the defaultLocale & isInitial true
        AppLocale.currentLocale shouldBe Locale.getDefault()
        AppLocale.isInitial shouldBe true
    }

    @Before
    fun resetAppLocale() {
        setAppLocaleField("isInitial", true)

        setAppLocaleField("appLocaleRepository", null)

        setAppLocaleField("supportedLocales", listOf<Locale>())
        setAppLocaleField("currentLocale", Locale.ROOT)
        setAppLocaleField("desiredLocale", Locale.ROOT)
    }

    private fun setAppLocaleField(name: String, newValue: Any?) {
        AppLocale::class.java.getDeclaredField(name).apply {
            isAccessible = true
            set(AppLocale, newValue)
        }
    }
}