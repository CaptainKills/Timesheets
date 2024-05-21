/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.7/userguide/building_java_projects.html in the Gradle documentation.
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // SQLite JDBC Driver
    implementation("org.xerial:sqlite-jdbc:3.30.1")

    // JSON
    implementation("org.json:json:20200518")

    // Aache POI
    implementation("org.apache.poi:poi:5.0.0")
    implementation("org.apache.poi:poi-ooxml:5.0.0")
    implementation("org.apache.poi:poi-ooxml-lite:5.0.0")
    implementation("org.apache.xmlbeans:xmlbeans:4.0.0")
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use JUnit Jupiter test framework
            useJUnitJupiter("5.10.1")
        }
    }


// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

application {
    // Define the main class for the application.
    mainClass = "nl.triantis.timesheets.Main"
}
