plugins {
    id(Libs.plugin.library)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

common()
compose()
dagger()
lifecycle()
glide()

android {
    namespace = "com.solopov.coreui"
}
dependencies {
    api(projects.coreUiCompose)
    implementation(projects.coreCommon)

    implementation(*Libs.bundle.splashscreen)
}
