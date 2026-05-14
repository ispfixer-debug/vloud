plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.vito.app.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.vito.app.android"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 34
        versionCode = 4
        versionName = "1.0"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    kotlin {
        sourceSets {
            all {
                languageSettings {
                    //optIn("kotlin.RequiresOptIn")
                    optIn("androidx.compose.foundation.ExperimentalFoundationApi")
                    optIn("androidx.compose.material3.ExperimentalMaterial3Api")
                }
            }
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    flavorDimensions += "appType"
    productFlavors {
        create("client") {
            dimension = "appType"
            applicationId = "com.vito.client"
            versionName = "1.0.0-client"
            buildConfigField("String", "APP_TYPE", "\"CLIENT\"")
        }
        create("driver") {
            dimension = "appType"
            applicationId = "com.vito.driver"
            versionName = "1.0.0-driver"
            buildConfigField("String", "APP_TYPE", "\"DRIVER\"")
        }
        create("admin") {
            dimension = "appType"
            applicationId = "com.vito.admin"
            versionName = "1.0.0-admin"
            buildConfigField("String", "APP_TYPE", "\"ADMIN\"")
        }
    }
    buildTypes {
        debug {
            buildConfigField("Boolean", "DEBUG_BYPASS_LOGIN", "true")
            buildConfigField("String", "DEBUG_CLIENT_ID", "\"debug-client-001\"")
            buildConfigField("String", "DEBUG_DRIVER_ID", "\"debug-driver-001\"")
            buildConfigField("String", "DEBUG_ADMIN_ID", "\"debug-admin-001\"")
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            buildConfigField("Boolean", "DEBUG_BYPASS_LOGIN", "false")
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    buildToolsVersion = "35.0.0"
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.compose.animation)
    implementation(libs.compose.foundation)
    implementation(libs.play.services.location)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.kotlin.reflect)
    implementation(libs.zoomable)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.coil)
    implementation(libs.coil.video)

    api(libs.maps.compose)
    //implementation(libs.places)
    implementation(libs.maps.utils.ktx)

}

