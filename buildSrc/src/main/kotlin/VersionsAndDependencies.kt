/*
// Bootstrapping from "common-gradle-dependencies"
val commonVersions = CommonVersions()
val commonGradleClasspathDependencies = CommonGradleClasspathDependencies(commonVersions)
*/


object DependencyVersions {
    val kotlin = "2.1.0" // compatible with the compose version below
    val composeMultiplatform = "1.7.1"
    val kotlinxBenchmark = "0.4.13"
    val dokka = "2.0.0"
    val binaryCompatibilityValidator = "0.16.3"
}

val alignedPluginVersion = "0.9.1-SNAPSHOT"

// "x.y.z" indicates the version of the way of organizing the code,
// and the date indicates the version when the dependency versions are updated.
val commonGradleDependenciesVersion = "0.10.0-20241203-SNAPSHOT"

// This is the source dependency version. There is another build source dependency in "buildSrc/build.gradle.kts".
val pluginProjectSourceDependencyStableCommonGradleDependenciesVersion = "0.9.0-20241203".apply {
    require(!endsWith("SNAPSHOT")) // comment this out when debugging and testing snapshots
}
