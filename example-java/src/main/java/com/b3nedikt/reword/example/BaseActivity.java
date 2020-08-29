package com.b3nedikt.reword.example;

import android.content.Context;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

import dev.b3nedikt.app_locale.AppLocale;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(AppLocale.wrap(newBase)));
    }

    @Override
    public Resources getResources() {
        return AppLocale.wrap(getBaseContext()).getResources();
    }
}
