plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    api(project(":service-api"))
    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}
