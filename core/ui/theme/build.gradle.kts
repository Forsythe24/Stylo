plugins {
    id(Libs.plugin.library)
    id(libs.plugins.compose.compiler.get().pluginId) version libs.versions.kotlin
}

common()
compose()
dagger()

dependencies {
    implementation(projects.coreCommon)
    implementation(*Libs.bundle.splashscreen)
    implementation(*Libs.bundle.composeTheme)


}
android {
    namespace = "com.solopov.coruitheme"
}
