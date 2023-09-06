plugins {
    kotlin("multiplatform")
    id ("com.android.application")
    id("org.jetbrains.compose")
}

kotlin {
    androidTarget()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
            }
        }
    }
}

android {
    namespace = "com.cerve.co.familyfeudincompose"
    compileSdk = libs.versions.android.target.sdk.orNull?.toInt()

    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")

    defaultConfig {
        applicationId = "com.cerve.co.familyfeudincompose"
        minSdk = libs.versions.android.min.sdk.orNull?.toInt()
        targetSdk = libs.versions.android.target.sdk.orNull?.toInt()
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    packaging {
        resources.excludes.add("META-INF/licenses/**")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
    kotlin {
        jvmToolchain(17)
    }
}
