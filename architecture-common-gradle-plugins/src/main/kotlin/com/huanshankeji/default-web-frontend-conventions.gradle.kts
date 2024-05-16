package com.huanshankeji

plugins {
    id("com.huanshankeji.kotlin-multiplatform-js-browser-app-conventions")
    id("org.jetbrains.compose")
}

interface Extension {
    val htmlGenerationProjectPath: Property<String>
}

val extension = extensions.create<Extension>("defaultWebFrontendConventions")

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    js {
        browser {
            commonWebpackConfig {
                outputFileName = "app.js"
            }
            testTask {
                testLogging.showStandardStreams = true
                useKarma {
                    useChromeHeadless()
                    useFirefox()
                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        jsMain {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.runtime)
            }
        }
    }
}


val generatedResourcesFile = buildDir.resolve("generatedResources")

tasks.named("jsProcessResources") {
    val htmlGenerationRun = tasks.getByPath(
        extension.htmlGenerationProjectPath.getOrElse(project.path + ":html-generation") + ":run"
    ) as JavaExec
    htmlGenerationRun.args(generatedResourcesFile.absolutePath)
    dependsOn(htmlGenerationRun)
}

kotlin.sourceSets { get("jsMain").resources.srcDir(generatedResourcesFile) }
