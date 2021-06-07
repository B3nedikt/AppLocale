plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:4.2.1")
    implementation(kotlin("gradle-plugin", "1.5.10"))
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.4.32")
}