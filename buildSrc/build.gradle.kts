plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.4.1")
    implementation(kotlin("gradle-plugin", "1.8.10"))
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.7.20")
}