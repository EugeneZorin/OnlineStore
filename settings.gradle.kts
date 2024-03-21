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

rootProject.name = "Online Store"
include(":app")
include(":domain")
include(":core")
include(":features")
include(":features:registration")
include(":domain:registration")
include(":data")
include(":features:catalog")
include(":features:product")
include(":features:profile")
include(":features:basket")
include(":features:home")
include(":features:stock")
include(":domain:catalog")
include(":domain:profile")
include(":domain:product")
