plugins {
    // it extends from generic java plugin.
    // designed to create java libraries.
    // add scope api
    id("java-library")
    // Allow us to publish the artifact in mvn repositories.
    // generate pom necessary files.
    // configure publishing{} config block
    id("maven-publish")
    // With "apply false" we will download the plugin but gradle does not apply it in the project
    // but will be available to use its utils.
    // so we won't run bootJar.
    id("org.springframework.boot") version "3.5.6" apply false
    // Gradle does not have native dependency management features.
    // it allows us to use dependencyManagement code block, mvn does support dependencyManagement natively.
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.dazt"
version = "0.0.1-SNAPSHOT"
description = "Common library for exception handling and propagation across microservices"

java {
    // Setting up which version use to compile.
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
    // Library docs config.
    withSourcesJar()
    withJavadocJar()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencyManagement {
    imports {
        // Here we want to use the plugin just to coordinate the BOMs.\
        /**
         * What are the BOMs?: Bills of materials, catalog version to avoid declaring version in or dependencies
         * block for some libraries.
         * Plugin will download this POM, process and apply the versions in our spring implemented libraries.
         * */
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}


repositories {
    mavenCentral()
}

dependencies {
    //We don't need to package this inside the jar, ms should already have it.
    compileOnly("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.springframework.boot:spring-boot-starter-validation")
    compileOnly("org.slf4j:slf4j-api")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-web")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// We don't want to create a fat jar.
// This is only necessary when we are downloading and activating springboot plugin, in this case is downloaded but deactivated.
//tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
//    enabled = false
//}

tasks.named<Jar>("jar") {
    enabled = true
}