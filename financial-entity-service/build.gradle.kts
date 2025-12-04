plugins {
	kotlin("jvm") version "2.2.21"
	kotlin("plugin.spring") version "2.2.21"
	id("org.springframework.boot") version "4.0.0"
	id("io.spring.dependency-management") version "1.1.7"
    id("com.google.cloud.tools.jib") version "3.5.1" // docker image generation
}

group = "com.loptur"
version = "1.0.0"
description = "Financial Entity Service"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2025.1.0"

dependencies {
    /* KOTLIN */
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    /* SPRING BOOT */
	implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    /* SPRING CLOUD CONFIG CLIENT */
	implementation("org.springframework.cloud:spring-cloud-starter-config")
    /* SPRING CLOUD EUREKA CLIENT */
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    /* TESTING */
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-test-autoconfigure")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("org.springframework.boot:spring-boot-starter-actuator-test")
    testImplementation("org.assertj:assertj-core:3.27.6")
    testImplementation("com.ninja-squad:springmockk:5.0.1")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jib.to.image = "turanzas/financial-entity:${project.version}"