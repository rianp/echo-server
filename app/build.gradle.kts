/*
 *
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
//    checkstyle
}

//checkstyle {
//    configFile = file("config/checkstyle/checkstyle.xml")
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
