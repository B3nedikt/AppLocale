package com.b3nedikt.restring.example;

import android.app.Application;

import dev.b3nedikt.app_locale.AppLocale;
import dev.b3nedikt.reword.RewordInterceptor;
import io.github.inflationx.viewpump.ViewPump;

class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppLocale.setSupportedLocales(Locales.APP_LOCALES);

        ViewPump.init(ViewPump.builder()
                .addInterceptor(RewordInterceptor.INSTANCE)
                .build()
        );
    }
}