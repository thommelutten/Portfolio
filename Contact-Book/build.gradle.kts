import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
plugins {
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.kotlin.kapt") version "1.4.32"
    jacoco
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
    implementation("org.jacoco:org.jacoco.core:0.8.4")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")
    testImplementation(kotlin("test-junit"))

}


tasks.test {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.isEnabled = true
        csv.isEnabled = false
        html.destination = layout.buildDirectory.dir("jacocoHtml").get().asFile
    }
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