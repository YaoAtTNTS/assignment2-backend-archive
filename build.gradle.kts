import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.xy"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.alibaba:fastjson:1.2.70")
	implementation("commons-lang:commons-lang:2.6")
	implementation("com.baomidou:mybatis-plus-boot-starter:3.1.2") {
		exclude(group = "com.baomidou", module = "mybatis-plus-generator")
	}
	implementation("com.alibaba:druid-spring-boot-starter:1.1.22")
	implementation("mysql:mysql-connector-java:8.0.16")
	implementation("org.hibernate:hibernate-validator:6.0.17.Final")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
