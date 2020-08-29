package com.b3nedikt.reword.example;

import java.util.*;

class Locales {
    private static final Locale LOCALE_AUSTRIAN_GERMAN = new Locale("de", "AT");

    static final List<Locale> APP_LOCALES =
            Arrays.asList(Locale.ENGLISH, Locale.US, Locales.LOCALE_AUSTRIAN_GERMAN);
}
