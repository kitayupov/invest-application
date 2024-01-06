plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "ru.devkit.investapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.devkit.investapplication"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    // Navigation
    val navVersion = "2.7.6"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    // Test
    androidTestImplementation("androidx.navigation:navigation-testing:$navVersion")
    androidTestImplementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    androidTestImplementation("androidx.navigation:navigation-runtime-ktx:$navVersion")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation(kotlin("test"))
    debugImplementation("androidx.fragment:fragment-testing:1.6.2")
    androidTestImplementation("io.mockk:mockk-android:1.13.3")
    // Dagger
    implementation("com.google.dagger:dagger:2.46.1")
    kapt("com.google.dagger:dagger-compiler:2.46.1")
    // DI
    implementation(project(":common-di"))
    // Feature
    implementation(project(":feature-splash-screen"))
    implementation(project(":feature-portfolio"))
    implementation(project(":feature-stock-history"))
    implementation(project(":feature-summary"))
    // Domain
    implementation(project(":domain-repository-impl"))
    implementation(project(":service-impl"))
    // Mock
    implementation(project(":service-mock-impl"))
}
