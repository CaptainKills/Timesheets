/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.7/userguide/building_java_projects.html in the Gradle documentation.
 * This project uses @Incubating APIs which are subject to change.
 */
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
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

    // Collections
    implementation("org.apache.commons:commons-compress:1.20")
    implementation("org.apache.commons:commons-collections4:4.4")
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use JUnit Jupiter test framework
            useJUnitJupiter("5.10.1")
        }
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

// tasks.withType<Jar> {
//     manifest {
//         attributes["Main-Class"] = "nl.triantis.timesheets.Main"
//     }
// }

// shadowJar {
//     archiveBaseName.set("timesheets_shadow")
//
//     manifest {
//         attributes["Main-Class"] = "nl.triantis.timesheets.Main"
//     }
// }

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("timesheets-shadow")
        archiveClassifier.set("")
        archiveVersion.set("")

        mergeServiceFiles()

        manifest {
            attributes["Main-Class"] = "nl.triantis.timesheets.Main"
        }

        dependencies {
            include(dependency("org.json:"))
            include(dependency("org.xerial:"))
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}

