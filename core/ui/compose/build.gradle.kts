plugins {
    id(Libs.plugin.library)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

common()
compose()
dagger()

dependencies {
    api(projects.coreUiTheme)
    api(*Libs.bundle.composeTheme)
    api(*Libs.bundle.composeKit)

    implementation(projects.coreCommon)
    implementation(*Libs.bundle.window)
    
}

android {
    namespace = "com.solopov.coreuicompose"
}
