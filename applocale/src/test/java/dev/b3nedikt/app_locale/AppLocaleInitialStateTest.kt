package dev.b3nedikt.app_locale

import android.os.Build
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.amshove.kluent.shouldBe
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.util.*

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
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
        AppLocale.javaClass.getDeclaredField(name).setFinalStatic(newValue)
    }

    private fun Field.setFinalStatic(newValue: Any?) {
        isAccessible = true
        val modifiersField: Field = Field::class.java.getDeclaredField("modifiers")
        modifiersField.isAccessible = true
        modifiersField.setInt(this, modifiers and Modifier.FINAL.inv())
        set(null, newValue)
    }
}