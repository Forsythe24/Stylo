plugins {
    id(Libs.plugin.library)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

common()
compose()
dagger()
lifecycle()
unitTests()

android {
    namespace = "com.solopov.feature_favorites_impl"
}

dependencies {
    implementation(projects.featureFavoritesApi)
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)
    implementation(Libs.bundle.googleServicesAuth)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
}
