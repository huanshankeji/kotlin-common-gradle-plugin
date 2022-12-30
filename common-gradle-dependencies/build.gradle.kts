import com.huanshankeji.generateKotlinVersion

plugins {
    conventions
}

// "x.y.z" indicates the version of the way of organizing the code,
// and the date indicates the version when the dependency versions are updated.
version = "0.3.3-20221230-SNAPSHOT"

generateKotlinVersion(kotlinVersion)

gradlePlugin {
    plugins {
        scriptPlugin(
            "com.huanshankeji",
            "common-gradle-dependencies-dummy-plugin",
            "A dummy plugin defined to enable publishing the package to the Gradle Plugin Portal"
        )
    }
}