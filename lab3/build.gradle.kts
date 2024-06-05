plugins {
    id("java")
    id("org.hibernate.orm") version "6.4.4.Final"
    id ("org.springframework.boot") version "3.2.3"
    id ("io.spring.dependency-management") version "1.1.4"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}



dependencies {
    implementation(project(":Service"))
    implementation(project(":Controller"))

    implementation ("org.springframework.boot:spring-boot-starter")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    implementation ("org.springframework.boot:spring-boot-starter-data-rest")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("javax.persistence:javax.persistence-api:2.2")

    implementation("org.postgresql:postgresql")

    compileOnly ("org.projectlombok:lombok:1.18.30")
    annotationProcessor ("org.projectlombok:lombok:1.18.30")

    testCompileOnly ("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.30")


}

tasks.test {
    useJUnitPlatform()
}