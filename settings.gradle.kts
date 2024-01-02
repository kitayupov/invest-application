pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Invest Application"
include(":app")
include(":feature-portfolio")
include(":feature-stock-history")
include(":feature-summary")
include(":service-api")
include(":service-impl")
include(":service-mock-impl")
include(":domain-repository")
include(":ui-model")
include(":utils")
