plugins {
    kotlin("jvm") version "1.3.61"
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit:junit:4.12")
    implementation ("org.apache.commons:commons-math3:3.6.1")
    implementation ("org.apache.commons:commons-collections4:4.0")
}

tasks.withType<Wrapper> {
  gradleVersion = "5.3.1"
  distributionType = Wrapper.DistributionType.ALL
}
