import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
plugins {
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.kotlin.kapt") version "1.4.32"
    application

}

group = "me.selenium"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    jcenter()
}

apply(plugin = "kotlin-kapt")

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")
    implementation("com.squareup.moshi:moshi:1.12.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")
    testImplementation(kotlin("test-junit"))

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}