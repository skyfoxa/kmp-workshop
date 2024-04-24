plugins {
    id("org.jetbrains.kotlin.multiplatform")
    kotlin("plugin.serialization").version("1.9.10")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project.dependencies.platform(libs.ktor.bom))
                implementation(libs.ktor.client.contentnegotiation)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.resources)
                implementation(libs.ktor.serialization.json)
            }
        }
        jvmMain {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}