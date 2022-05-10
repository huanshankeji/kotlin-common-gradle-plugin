package com.huanshankeji

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

class GenerateKotlinJsBrowserWebrootForVertxWebPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        val extension = extensions.create<Extension>("generateKotlinJsResources")


        // see: https://play.kotlinlang.org/hands-on/Full%20Stack%20Web%20App%20with%20Kotlin%20Multiplatform/04_Frontend_Setup

        /*val jsBrowserDistributionTask by lazy {
            tasks.getByPath(extension.webFrontendProjectPath.get() + ":jsBrowserDistribution") as KotlinWebpack
        }*/
        val jsBrowserProductionWebpack by lazy {
            tasks.getByPath(extension.webFrontendProjectPath.get() + ":jsBrowserProductionWebpack") as KotlinWebpack
        }
        val copyJsBrowserDistributionToResourcesWebroot = "copyJsBrowserDistributionToResourcesWebroot"
        val browserDistributionResourcesDirectory = buildDir.resolve("browserDistributionResources")

        tasks.register<Copy>(copyJsBrowserDistributionToResourcesWebroot) {
            dependsOn(jsBrowserProductionWebpack)
            from(jsBrowserProductionWebpack.destinationDirectory)
            include("*.html", "*.css", "*.js")
            into(browserDistributionResourcesDirectory.resolve("webroot"))
        }

        tasks.named<Copy>("processResources") {
            dependsOn(copyJsBrowserDistributionToResourcesWebroot)
        }

        sourceSets.main { resources.srcDir(browserDistributionResourcesDirectory) }
    }

    interface Extension {
        val webFrontendProjectPath: Property<String>
    }
}