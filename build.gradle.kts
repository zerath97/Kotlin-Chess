plugins {
    kotlin("jvm") version "2.0.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin standard library
    implementation(kotlin("stdlib"))

    // JUnit 5 dependencies for testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1") // JUnit 5 API
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1") // JUnit 5 Engine

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}