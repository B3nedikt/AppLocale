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

    @Test()
    fun localeSaveAndRetrieve() {

        // Given a repository
        val repository = SharedPrefsAppLocaleRepository(ApplicationProvider.getApplicationContext())

        // When we set the desired locale to German
        repository.desiredLocale = Locale.GERMAN

        // Then the desired Locale should also be German, when we recreate the repository
        val repository2 = SharedPrefsAppLocaleRepository(ApplicationProvider.getApplicationContext())
        repository2.desiredLocale shouldBe Locale.GERMAN
    }
}