plugins { kotlin("jvm") version "1.9.21" }

group = "au.id.oconal"

version = "1.0-SNAPSHOT"

var junitVersion = "5.10.1"
var kotlinxVersion = "1.7.3"

repositories { mavenCentral() }

dependencies {
  testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
  testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
  testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinxVersion")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxVersion")
}

tasks.test { useJUnitPlatform() }

kotlin { jvmToolchain(20) }
