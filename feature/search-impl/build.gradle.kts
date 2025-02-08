plugins {
    id(Libs.plugin.library)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

common()
compose()
dagger()
lifecycle()

android {
    namespace = "com.solopov.feature_search_impl"
}

dependencies {
    implementation(projects.featureSearchApi)
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)
    implementation(Libs.bundle.googleServicesAuth)
    implementation(Libs.bundle.rxJava)
}
