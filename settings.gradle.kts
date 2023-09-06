rootProject.name = "FamilyFeudInCompose"

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    versionCatalogs {
        create("libs") {
            from(files("./libs.versions.toml"))
        }
    }
}

include(":app")
include(":shared")
