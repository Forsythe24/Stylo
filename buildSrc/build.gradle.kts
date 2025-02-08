
plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.android.tools.build:gradle:8.5.0")
    api(kotlin("gradle-plugin:2.0.0"))
    implementation("com.squareup:javapoet:1.13.0")
}
