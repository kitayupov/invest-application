plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    // Test
    testImplementation(kotlin("test"))
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
}
