plugins {
    kotlin("jvm") version "2.0.20"
}

group = "ru.ntcrckr"
version = "1.0-SNAPSHOT"

kotlin {
    jvmToolchain(22)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:2.1.0")
    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
}

tasks.test {
    useJUnitPlatform()
}