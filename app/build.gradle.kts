plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.googleGmsGoogleServices)
}

android {
    namespace = "com.example.onlinestore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.onlinestore"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":features:sign-up"))
    implementation(project(":features:sign-in"))
    implementation(project(":features:catalog"))
    implementation(project(":features:characteristic"))
    implementation(project(":domain:authorization"))
    implementation(project(":domain:catalog"))
    implementation(project(":domain:characteristic"))
    implementation(project(":data"))
    implementation(project(":core"))

    implementation(libs.hilt)
    implementation(project(":domain:catalog"))
    ksp(libs.hilt.compiler)

    implementation(libs.firebase.storage)
    implementation(libs.firebase.database)
    implementation(libs.firebase.firestore)
    implementation(libs.room.ktx)
    implementation(libs.androidx.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation(libs.espresso.core)


}