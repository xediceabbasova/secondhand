plugins{
    application
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.data:spring-data-elasticsearch:4.4.12")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")

}

tasks.test {
    useJUnitPlatform()
}
application {
    mainClass.set("com.company.secondhand.SecondhandApplication")
}