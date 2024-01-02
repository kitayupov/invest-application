plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
}

dependencies {
    api(project(":service-api"))

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Dagger
    implementation("com.google.dagger:dagger:2.46.1")
    kapt("com.google.dagger:dagger-compiler:2.46.1")
}
