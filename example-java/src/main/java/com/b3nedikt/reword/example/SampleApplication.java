package com.b3nedikt.reword.example;

import android.app.Application;

import dev.b3nedikt.app_locale.AppLocale;
import dev.b3nedikt.app_locale.SharedPrefsAppLocaleRepository;
import dev.b3nedikt.reword.RewordInterceptor;
import dev.b3nedikt.viewpump.ViewPump;

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppLocale.setSupportedLocales(Locales.APP_LOCALES);

        AppLocale.setAppLocaleRepository(new SharedPrefsAppLocaleRepository(this));

        ViewPump.init(ViewPump.builder()
                .addInterceptor(RewordInterceptor.INSTANCE)
                .build()
        );
    }
}