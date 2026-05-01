plugins {
    id("java")
    application
    id("org.openjfx.javafxplugin") version "0.0.13"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Yahoo Finance API (пример внешней зависимости)
    implementation("com.yahoofinance-api:YahooFinanceAPI:3.17.0")
}

javafx {
    version = "21"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("org.example.App1")
}

tasks.test {
    useJUnitPlatform()
}

// Настройка кодировки для Windows
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
