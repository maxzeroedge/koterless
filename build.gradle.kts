import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	application
	id("org.springframework.boot") version "2.3.1.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
}

group = "com.palashmax"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

val jackson_version = "2.11.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-websocket")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${jackson_version}")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
	implementation("com.fasterxml.jackson.core:jackson-databind:${jackson_version}")
	implementation("com.fasterxml.jackson.core:jackson-annotations:${jackson_version}")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${jackson_version}")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:${jackson_version}")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		// freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

/*val args: List<String> by project

task("bootRun") {
	if (project.hasProperty("args")) {
		var args = project.args.split(',')
	}
}*/

application {
	mainClass.set("com.palashmax.koterless.KoterlessApplicationKt")
}