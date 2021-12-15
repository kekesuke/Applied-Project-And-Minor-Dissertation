plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id ("org.jetbrains.kotlin.plugin.serialization")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.example.applied_project_and_minor_dissertation.android"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    packagingOptions {
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/LICENSE")
        resources.excludes.add("META-INF/license.txt")
        resources.excludes.add("META-INF/NOTICE")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("META-INF/ASL2.0")

    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation("io.ktor:ktor-client-core:1.6.6")
    implementation("io.ktor:ktor-client-android:1.6.6")
    implementation("io.ktor:ktor-client-serialization:1.6.6")
    implementation("io.ktor:ktor-client-logging:1.6.6")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0-alpha02")

}