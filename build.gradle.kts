import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    // kotlin("jvm") version "1.6.21"
    application
}

group = "derek.practice"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    // kotlinOptions.jvmTarget = "1.8"
    kotlinOptions {
        freeCompilerArgs = listOf(
            "-Xuse-k2",
            "-Xjdk-release=11",
            "-Xbackend-thread=4",
        )
        jvmTarget = "11"
        languageVersion = "1.7"
    }
}

application {
    mainClass.set("MainKt")
}