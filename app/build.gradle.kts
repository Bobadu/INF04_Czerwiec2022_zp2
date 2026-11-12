plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.kursy"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.example.kursy"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
}
