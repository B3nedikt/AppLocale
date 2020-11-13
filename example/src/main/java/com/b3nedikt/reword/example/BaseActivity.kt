package com.b3nedikt.reword.example

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.ViewPumpAppCompatDelegate
import dev.b3nedikt.app_locale.AppLocale

abstract class BaseActivity : AppCompatActivity() {

    private val appCompatDelegate: AppCompatDelegate by lazy {
        ViewPumpAppCompatDelegate(
                baseDelegate = super.getDelegate(),
                baseContext = this,
                wrapContext = { baseContext -> AppLocale.wrap(baseContext) }
        )
    }

    override fun getDelegate(): AppCompatDelegate {
        return appCompatDelegate
    }
}