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
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "invest-application"

include(":app")

include(":common-di")

include(":common-navigation")

include(":common-ui")
include(":common-ui-models")
include(":common-ui-utils")
include(":common-utils")

include(":service-api")
include(":service-impl")
include(":service-mock-impl")

include(":domain-repository-api")
include(":domain-repository-impl")

include(":feature-splash-screen")
include(":feature-portfolio")
include(":feature-stock-history")
include(":feature-summary")
