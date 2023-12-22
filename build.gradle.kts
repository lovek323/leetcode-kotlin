plugins {
    kotlin("jvm") version "1.9.21"
}

group = "au.id.oconal"
version = "1.0-SNAPSHOT"

var junitVersion = "5.10.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}