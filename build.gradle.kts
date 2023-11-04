plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "ie.setu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation ("org.slf4j:slf4j-simple:1.7.36") //New version was not working correctly
    implementation ("io.github.microutils:kotlin-logging:2.1.23") // New version was not working correctly
    implementation("com.thoughtworks.xstream:xstream:1.4.18")  // Xstream from maven
    implementation("org.codehaus.jettison:jettison:1.4.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}