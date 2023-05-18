/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/8.0.2/userguide/building_java_projects.html
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    checkstyle
}

//tasks {
//    javadoc {
//        options {
//            (this as CoreJavadocOptions).addStringOption("Xdoclint:none", "-quiet")
//        }
//    }
//}

checkstyle {
    toolVersion = "10.11.0"
}

//checkstyle {
//    configProperties = mapOf(
//            "checkstyle.suppression.files" to "javadoc.suppressions.xml"
//    )
//}

//java {
//    toolchain {
//        languageVersion.set(JavaLanguageVersion.of(20))
//    }
//}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")

    testImplementation("org.mockito:mockito-core:3.12.4")


    // This dependency is used by the application.
    implementation("com.google.guava:guava:31.1-jre")

}

application {
    // Define the main class for the application.
    mainClass.set("echo.server.App")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

//tasks.withType<Checkstyle>().configureEach {
//    reports {
//        xml.required.set(false)
//        html.required.set(true)
//        html.stylesheet = resources.text.fromFile("config/checkstyle/checkstyle.xml")
//    }
//}
