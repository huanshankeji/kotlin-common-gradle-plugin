plugins {
    id("org.gradle.kotlin.kotlin-dsl")
    id("com.huanshankeji.team.with-group")

    id("com.huanshankeji.java-1-8-compatibility-publish-conventions")
    id("com.gradle.plugin-publish")
    id("com.huanshankeji.team.default-github-packages-maven-publish")
}

repositories {
    //mavenLocal() // comment out when not needed
    gradlePluginPortal()
}

dependencies {
    // Not specifying version can cause build issues when added to a project's buildscript dependencies if the version in the "buildSrc" build script is different.
    implementation(kotlin("gradle-plugin"))
    // These 2 dependencies are implicitly added with the Gradle's embedded Kotlin version if not added explicitly.
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
}

kotlin.jvmToolchain(8)


gradlePlugin {
    website.set(GITHUB_URL)
    vcsUrl.set(GITHUB_GIT_URL)
    plugins.all { tags.set(listOf("kotlin", "kotlin-multiplatform")) }
}
