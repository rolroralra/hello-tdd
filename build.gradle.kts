val apacheCommonsLang3Version: String by project
val guavaVersion: String by project
val wiremockVersion: String by project
val wiremockSpringBootVersion: String by project
val okHttpVersion: String by project

plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // Remove Tomcat dependency
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }

    // Add Jetty dependency
    implementation("org.springframework.boot:spring-boot-starter-jetty")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.apache.commons:commons-lang3:$apacheCommonsLang3Version")
    implementation("com.google.guava:guava:$guavaVersion")

    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")

    // https://www.kevinhooke.com/2024/05/03/using-wiremock-with-spring-boot-3-and-junit-4/
    // wiremock-spring-boot 의존성 추가 및 wiremock 의존성 제외
    testImplementation("com.maciejwalkowiak.spring:wiremock-spring-boot:$wiremockSpringBootVersion") {
        exclude(group = "org.wiremock", module = "wiremock")
    }

    // wiremock-standalone 의존성 추가
    testImplementation("org.wiremock:wiremock-standalone:$wiremockVersion")

    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.rest-assured:spring-mock-mvc")
    testImplementation("org.springframework.restdocs:spring-restdocs-restassured")

    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")

    testImplementation("com.squareup.okhttp3:mockwebserver:$okHttpVersion")
    testImplementation("com.squareup.okhttp3:okhttp:$okHttpVersion")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<JavaExec> {
    jvmArgs("-XX:+EnableDynamicAgentLoading", "-Xshare:off")
}

tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs("-XX:+EnableDynamicAgentLoading", "-Xshare:off")
}

tasks.test {
    outputs.dir(project.extra["snippetsDir"]!!)
}

tasks.register<Test>("unitTest") {
    description = "Run unit tests"
    group = "verification"

    useJUnitPlatform {
        includeTags("unitTest")
    }
}

tasks.register<Test>("integrationTest") {
    description = "Run integration tests"
    group = "verification"

    useJUnitPlatform {
        includeTags("integrationTest")
    }
}

tasks.register<Test>("acceptanceTest") {
    description = "Run acceptance tests"
    group = "verification"

    useJUnitPlatform {
        includeTags("acceptanceTest")
    }
}

tasks.asciidoctor {
    inputs.dir(project.extra["snippetsDir"]!!)
    dependsOn(tasks.test)
}
