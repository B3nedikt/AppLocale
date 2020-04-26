[ ![Download](https://api.bintray.com/packages/b3nedikt/applocale/applocale/images/download.svg?version=1.2.5) ](https://bintray.com/b3nedikt/applocale/applocale/1.2.5/link)
[![Build Status](https://travis-ci.org/B3nedikt/applocale.svg?branch=master)](https://travis-ci.org/B3nedikt/applocale)
[![codecov](https://codecov.io/gh/B3nedikt/applocale/branch/master/graph/badge.svg)](https://codecov.io/gh/B3nedikt/applocale)
[![Documentation](https://img.shields.io/badge/docs-documentation-green.svg)](https://b3nedikt.github.io/AppLocale/)

## App Locale 1.2.5
AppLocale is a android library to update the app language dynamically.

### 1. Add dependency

```groovy
// Manages the Locale used by the app
implementation 'dev.b3nedikt.applocale:applocale:1.2.5'
```

### 2. Initialize

Initialize AppLocale  in your Application class:

```kotlin
// The languages supported by our app, normally the ones we have strings.xml files for
// in the resources. If you dont set this, it will be assumed the app supports every language
// set programmatically using this lib.
AppLocale.supportedLocales = listOf(Locale.ENGLISH, Locale.French)

// Optional: Persist changes to the desiredLocale to sharedPreferences
AppLocale.appLocaleRepository = SharedPrefsAppLocaleRepository(this)
```

### 2. Add to the base activity

If the app has a base activity, this can go there, otherwise it needs to be added to every activity:

```kotlin
abstract class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(AppLocale.wrap(newBase))
    }

    override fun getResources(): Resources {
        return AppLocale.wrap(baseContext).resources
    }
}
```

### 3. Update the app language

Now we can just set the desiredLocale to the Locale we want our app to use:

```kotlin
AppLocale.desiredLocale = Locale.FRENCH
```

When the activity gets restarted, all texts will be localized using the new Locale.

## Update the app language without restarting the activity

If you want to change the apps Locale without restarting the activity,
you need to add the following additional dependencies:

```groovy
// Needed to intercept view inflation
implementation 'io.github.inflationx:viewpump:2.0.3'

// Allows to update the text of views at runtime without recreating the activity
implementation 'dev.b3nedikt.reword:reword:1.1.0'
```

Initialize ViewPump & Reword in the application class:

```kotlin
// To dynamically update views we need to intercept view inflation and update
// the text of each view. The libraries ViewPump and reword do exactly that when setup
// like this:
ViewPump.init(ViewPump.builder()
        .addInterceptor(RewordInterceptor)
        .build())
```

To use ViewPump we need to additionally wrap the context with ViewPump in our base activity:

```kotlin
abstract class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(AppLocale.wrap(newBase)))
    }

    override fun getResources(): Resources {
        return AppLocale.wrap(baseContext).resources
    }
}
```

After setting the new locale you need to call reword
to update all views which have been inflated from xml:

```kotlin
// If we want to update the app language without restarting the activity,
// we need to perform the update of the texts manually:
val rootView = window.decorView.findViewById<ContentFrameLayout>(android.R.id.content)
Reword.reword(rootView)
```

If you have changed the texts of views in code, you need to update these
texts manually of course.

## License

```
Copyright 2020 applocale Contributors.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
