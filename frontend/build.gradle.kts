// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.3.0")
    }
}