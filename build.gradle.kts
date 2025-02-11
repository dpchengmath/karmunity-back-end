buildscript {
	dependencies {
		classpath("com.h2database:h2:2.2.224")
	}
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	gradlePluginPortal()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-logging")
	implementation("org.springframework.session:spring-session-core")
	implementation("org.springframework.security:spring-security-config")
	implementation("org.springframework.security:spring-security-core")
	implementation("org.springframework.security:spring-security-web")
	implementation("org.springframework.security:spring-security-crypto")
	implementation("com.fasterxml.jackson.core:jackson-databind")
	implementation("org.hibernate:hibernate-core:6.2.1.Final")
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
	implementation("jakarta.validation:jakarta.validation-api:3.0.0")
	implementation("org.hibernate:hibernate-validator:6.2.1.Final")
	implementation("org.glassfish:jakarta.el:4.0.0")
	implementation("org.postgresql:postgresql:42.7.2")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
	implementation("org.projectlombok:lombok:1.18.24")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

plugins {
	java
	kotlin("jvm") version "2.1.0"
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.flywaydb.flyway") version "9.5.0"
	id("java")
	id("application")
}

flyway {
//	url = "jdbc:h2:file:./target/foobar"
//	user = "sa"
	url = "jdbc:postgresql://localhost:5432/karmunity_development"
	user = "postgres"
	password = "postgres"
	driver = "org.postgresql.Driver"
//	locations = arrayOf("filesystem:src/main/resources/db/migration")
	locations = arrayOf("classpath:db/migration")
	baselineOnMigrate = true
	cleanDisabled = false
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jar {
	manifest {
		attributes["Main-Class"] = "com.karmunity.KarmunityApplication"
	}
}