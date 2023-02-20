buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    tasks.withType<Javadoc>().all { enabled = false }
}
