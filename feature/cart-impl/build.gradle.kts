plugins {
    id(Libs.plugin.library)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

common()
compose()
dagger()
lifecycle()

android {
    namespace = "com.solopov.feature_cart_impl"
}

dependencies {
    implementation(projects.featureCartApi)
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)
    implementation(Libs.bundle.googleServicesAuth)
    implementation(Libs.bundle.rxJava)
}
