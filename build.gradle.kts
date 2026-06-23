plugins {
    java
    id("org.springframework.boot") version "3.5.15"
    id("io.spring.dependency-management") version "1.1.7"
//    id("org.graalvm.buildtools.native") version "0.10.6"
//    id("org.asciidoctor.jvm.convert") version "4.0.3"
}

group = "com.dev_of_blue"
version = "1.0.0"
description = "bakery_eureka_server for Spring Cloud"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

// extra["snippetsDir"] = file("build/generated-snippets")
extra["springCloudVersion"] = "2025.0.3"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    // runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // testImplementation("org.springframework.boot:spring-boot-testcontainers")
    // testImplementation("org.testcontainers:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// tasks.test {
//     outputs.dir(project.extra["snippetsDir"]!!)
// }
//
// tasks.asciidoctor {
//     inputs.dir(project.extra["snippetsDir"]!!)
//     dependsOn(tasks.test)
// }

//tasks.named("processAot") {
//    enabled = false
//}
