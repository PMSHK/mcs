plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.xrc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17



repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2023.0.3"

dependencies {
	/**
	 * Spring boot starters
	 */
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	/**
	 * Database
	 */
	implementation("com.fasterxml.jackson.core:jackson-databind:2.18.0")
	implementation("org.slf4j:slf4j-api:2.0.16")

	implementation("org.projectlombok:lombok:1.18.28")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	annotationProcessor("org.projectlombok:lombok:1.18.28")
	implementation("org.liquibase:liquibase-core")
	implementation ("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.0")
	implementation("org.projectlombok:lombok-mapstruct-binding:0.2.0")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
	implementation("redis.clients:jedis:4.3.2")


	/**
	 * Test containers
	 */
	implementation(platform("org.testcontainers:testcontainers-bom:1.20.3"))
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")
	testImplementation("ch.qos.logback:logback-classic:1.5.12")

	/**
	 * Tests
	 */
	testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
	testImplementation("org.assertj:assertj-core:3.26.3")
	testImplementation("org.junit.platform:junit-platform-launcher")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	/**
	 * Swagger
	 */
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

	/**
	 *
	 */
	implementation("org.apache.commons:commons-math3:3.6.1")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val test by tasks.getting(Test::class) {testLogging.showStandardStreams = true}

tasks.bootJar {
	archiveFileName.set("service.jar")
}