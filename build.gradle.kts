plugins {
    java
    id("org.springframework.boot") version "3.5.15"
    id("io.spring.dependency-management") version "1.1.7"
//    id("org.graalvm.buildtools.native") version "0.10.6"
//    id("org.asciidoctor.jvm.convert") version "4.0.3"
}

description = "Eureka Service Discovery Server for Bakery"

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
    // 2. Spring Boot Core & Web
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // 3. Spring Cloud & Discovery
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    implementation("org.springframework.cloud:spring-cloud-starter-config")

    // 8. Tooling & Lombok
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    // 9. Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // testImplementation("org.springframework.boot:spring-boot-testcontainers")
    // testImplementation("org.testcontainers:junit-jupiter")
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
