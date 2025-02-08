plugins {
    id(Libs.plugin.library)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

common()
compose()
dagger()
lifecycle()

android {
    namespace = "com.solopov.feauture_home_impl"
}

dependencies {
    implementation(projects.featureHomeApi)
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)
    implementation(Libs.bundle.googleServicesAuth)
    implementation(Libs.bundle.rxJava)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
}
