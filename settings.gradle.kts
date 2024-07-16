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
include(":features")
include(":features:sign-up")
include(":domain:authorization")
include(":data")
include(":domain:catalog")
include(":domain:profile")
include(":domain:product")
include(":features:catalog")
include(":features:characteristic")
include(":domain:characteristic")
include(":core")
include(":features:profile")
include(":features:sign-in")
