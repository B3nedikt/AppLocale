package dev.b3nedikt.app_locale

import android.os.Build
import androidx.test.core.app.ApplicationProvider
import org.amshove.kluent.shouldBe
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.*

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class SharedPrefsAppLocaleRepositoryTest {

    @Test
    fun localeSaveAndRetrieve() {

        // Given a repository
        val repository = SharedPrefsAppLocaleRepository(ApplicationProvider.getApplicationContext())

        // When we set the desired locale to German
        repository.desiredLocale = Locale.GERMAN

        // Then the desired Locale should also be German, when we recreate the repository
        val repository2 = SharedPrefsAppLocaleRepository(ApplicationProvider.getApplicationContext())
        repository2.desiredLocale shouldBe Locale.GERMAN
    }

    @Test
    fun initialValueIsNull() {

        // Given a new repository
        val repository = SharedPrefsAppLocaleRepository(ApplicationProvider.getApplicationContext())

        // Then the desired locale should be null
        repository.desiredLocale shouldBe null
    }

    @Test
    fun settingValueToNullResetsIt() {

        // Given a repository with the desired locale set to German
        val repository = SharedPrefsAppLocaleRepository(ApplicationProvider.getApplicationContext())
        repository.desiredLocale = Locale.GERMAN

        // When we set the desired locale to null
        repository.desiredLocale = null

        // Then the desired Locale should be null, when we recreate the repository
        val repository2 = SharedPrefsAppLocaleRepository(ApplicationProvider.getApplicationContext())
        repository2.desiredLocale shouldBe null
    }
}