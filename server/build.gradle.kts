plugins {
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    application
}

group = "dev.matsem.imagen"
version = "1.0.0"

application {
    mainClass.set("dev.matsem.imagen.ApplicationKt")
    val devMode = System.getenv("DEV_MODE")?.toBooleanStrictOrNull() ?: false
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$devMode")
}

dependencies {
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.desktop.currentOs)

    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}