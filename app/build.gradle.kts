plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}

android {
    namespace = "com.cerve.co.familyfeudincompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cerve.co.familyfeudincompose"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation ("androidx.activity:activity-compose:1.7.2")
    implementation ("androidx.compose.ui:ui:1.4.3")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation ("androidx.compose.material3:material3:1.2.0-alpha06")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
    implementation ("com.google.accompanist:accompanist-insets-ui:0.27.0")


    implementation ("androidx.navigation:navigation-compose:2.7.1")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.2")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.2")
}