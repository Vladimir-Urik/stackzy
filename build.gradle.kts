import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.4.30"
    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    id("org.jetbrains.compose") version "0.3.0-build152"
}

group = "com.theapache64"
version = "1.0.0-alpha01"

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit"))
    implementation(compose.desktop.currentOs)
    implementation(compose.materialIconsExtended)

    // Cyclone
    implementation("com.github.theapache64:cyclone:1.0.0-alpha02")

    // Dagger : A fast dependency injector for Android and Java.
    val daggerVersion = "2.31.2"
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")
    kaptTest("com.google.dagger:dagger-compiler:$daggerVersion")

    // Retrofit : A type-safe HTTP client for Android and Java.
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    // Decompose : Decompose
    val decomposeVersion = "0.1.8"
    implementation("com.arkivanov.decompose:decompose-jvm:$decomposeVersion")
    implementation("com.arkivanov.decompose:extensions-compose-jetbrains-jvm:$decomposeVersion")

    // Retrosheet : Turn Google Spreadsheet to JSON endpoint (for Android and JVM)
    implementation("com.theapache64:retrosheet:1.2.2")

    // Kotlinx.Serialization
    implementation("com.squareup.moshi:moshi:1.11.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.11.0")

    implementation("com.malinskiy:adam:0.2.3")

    // Arbor : Like Timber, just different.
    implementation("com.ToxicBakery.logging:arbor-jvm:1.34.109")

    testImplementation("org.mockito:mockito-inline:3.7.7")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    // DaggerMock
    testImplementation("com.github.fabioCollini.daggermock:daggermock:0.8.5")
    testImplementation("com.github.fabioCollini.daggermock:daggermock-kotlin:0.8.5")

    // Mockito Core : Mockito mock objects library core API and implementation
    testImplementation("org.mockito:mockito-core:3.7.7")

    // Expekt : An assertion library for Kotlin
    testImplementation("com.theapache64:expekt:0.0.1")

    implementation("com.github.theapache64:name-that-color:1.0.0-alpha02")


}

tasks.test {
    useJUnit()
    environment("ANDROID_HOME", System.getenv("ANDROID_HOME") ?: "/home/theapache64/Android/Sdk")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
    kotlinOptions.freeCompilerArgs += "-Xuse-experimental=androidx.compose.foundation.ExperimentalFoundationApi"
    kotlinOptions.freeCompilerArgs += "-Xuse-experimental=kotlin.io.path.ExperimentalPathApi"
}

compose.desktop {
    application {
        mainClass = "com.theapache64.stackzy.AppKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Stackzy"
        }
    }
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
}