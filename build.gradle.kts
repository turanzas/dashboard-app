plugins {
	kotlin("jvm") version "2.2.21"
    id("org.sonarqube") version "7.1.0.6387"
}

group = "com.loptur"
version = "1.0.0"
description = "Dashboard Application"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
    /* KOTLIN */
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
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