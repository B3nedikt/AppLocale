
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.b3nedikt.applocale/applocale/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.b3nedikt.applocale/applocale)
[![Build & Test](https://github.com/B3nedikt/AppLocale/actions/workflows/build-and-test.yaml/badge.svg)](https://github.com/B3nedikt/AppLocale/actions/workflows/build-and-test.yaml)
[![codecov](https://codecov.io/gh/B3nedikt/applocale/branch/master/graph/badge.svg)](https://codecov.io/gh/B3nedikt/applocale)
[![javadoc](https://javadoc.io/badge2/dev.b3nedikt.applocale/applocale/javadoc.svg)](https://javadoc.io/doc/dev.b3nedikt.applocale/applocale)

## App Locale 3.1.0

AppLocale is a android library to change the app language dynamically.

### 1. Add dependencies

In addition to appcompat from androidX which you most likely already use,
the following three dependencies are needed:

```groovy
// Manages the Locale used by the app
implementation 'dev.b3nedikt.applocale:applocale:3.1.0'

// Needed to intercept view inflation
implementation 'dev.b3nedikt.viewpump:viewpump:4.0.10'

// Allows to update the text of views at runtime without recreating the activity
implementation 'dev.b3nedikt.reword:reword:4.0.4'
```

### 2. Initialize

Initialize ViewPump & Reword in your `Application` class:

```kotlin
// To dynamically update views we need to intercept view inflation and update
// the text of each view. The libraries ViewPump and reword do exactly that when setup
// like this:
ViewPump.init(RewordInterceptor)
```

### 3. Inject into Context

If you use your application `Context` to retrieve string resources add the following to your
`Application` class:

```kotlin
class SampleApplication : Application() {

    ...

    override fun getResources(): Resources {
        return AppLocale.wrapResources(applicationContext, super.getResources())
    }
}
```

If you have a BaseActivity you can add this there, otherwise you have to add it to all of your activities:

```kotlin
abstract class BaseActivity : AppCompatActivity() {

    private val appCompatDelegate: AppCompatDelegate by lazy {
        ViewPumpAppCompatDelegate(
                baseDelegate = super.getDelegate(),
                baseContext = this,
                wrapContext = AppLocale::wrap
        )
    }

    override fun getDelegate(): AppCompatDelegate {
        return appCompatDelegate
    }
}
```

### 3. Update the app language

Now we can just set the desiredLocale to the Locale we want our app to use:

```kotlin
AppLocale.desiredLocale = Locale.FRENCH
```

When the activity gets restarted, all texts will be localized using the new Locale.

Alternativeley you can reload the UI like this:

```kotlin
// If we want to update the app language without restarting the activity,
// we need to perform the update of the texts manually:
val rootView = window.decorView.findViewById<ContentFrameLayout>(android.R.id.content)
Reword.reword(rootView)
```

If you use this approach and you changed the texts of views in code, you need to update these
texts manually of course.

## App Bundle support

If you use the new android app bundle (aab) format to distribute your app, android
will strip out all languages which are not the users devices default language.
To disable this add this to your build.gradle:

```groovy
android {
    bundle {
        language {
            // We want to be able to switch the locale at runtime using AppLocale!
            enableSplit = false
        }
    }
}
```

Alternatively load the languages dynamically using the playcore library as described in its
[Documentation](https://developer.android.com/guide/playcore/dynamic-delivery#lang_resources).

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
