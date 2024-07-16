plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    alias(libs.plugins.googleGmsGoogleServices)
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}


dependencies {
    
    implementation(project(":domain:authorization"))
    implementation(project(":domain:catalog"))

    implementation (libs.jbcrypt)
    implementation (libs.coil)

    implementation (libs.firebase.storage)
    implementation(libs.firebase.bom)

    implementation(libs.hilt)
    implementation(libs.firebase.database)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.firestore)
    implementation(libs.play.services.analytics.impl)
    implementation(libs.firebase.auth)
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.mockito:mockito-core:2.25.0")
    ksp(libs.hilt.compiler)
    implementation ("com.google.code.gson:gson:2.10.1")

    ksp(libs.room.compiler)
    implementation(libs.room)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.junit.ktx)

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
}

