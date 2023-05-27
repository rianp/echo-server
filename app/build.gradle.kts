import java.io.InputStream

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
//    id("com.adarshr.test-logger") version "3.2.0"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")

    testImplementation("org.mockito:mockito-core:3.12.4")
}

//application {
//    // Define the main class for the application.
//    mainClass.set("echo.server.ServerRunner")
//}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

tasks.create<JavaExec>("bootServer") {
    mainClass.set("echo.server.ServerRunner")
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.create<JavaExec>("bootClient") {
    mainClass.set("echo.client.ClientRunner")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
}

//tasks.named("bootServer") {
//    finalizedBy("bootClient")
//}
