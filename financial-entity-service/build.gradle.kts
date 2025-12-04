plugins {
	kotlin("jvm") version "2.2.21"
	kotlin("plugin.spring") version "2.2.21"
	id("org.springframework.boot") version "4.0.0"
	id("io.spring.dependency-management") version "1.1.7"
    id("com.google.cloud.tools.jib") version "3.5.1" // docker image generation
    id("org.sonarqube") version "7.1.0.6387"
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
	testImplementation("org.springframework.boot:spring-boot-starter-actuator-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:3.27.6")
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
    systemProperty("spring.profiles.active", "test")
}

sonar {
    properties {
        property("sonar.projectKey", "turanzas_dashboard-app")
        property("sonar.projectName", "Dashboard App")
        property("sonar.organization", "turanzas")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.token", System.getenv("SONAR_TOKEN"))
    }
}

jib.to.image = "turanzas/financial-entity:${project.version}"