plugins {
    id(Libs.plugin.library)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

compose()
common()


android {
    namespace = "com.solopov.corenavigation"
}
dependencies {
    api(*Libs.bundle.navigation)
    api(*Libs.bundle.kotlinSerialization)
    implementation(*Libs.bundle.browser)
}
