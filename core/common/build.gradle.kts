plugins {
    id(Libs.plugin.library)
}

common()
dagger()
lifecycle()


android {
    namespace = "com.solopov.corecommon"
}

dependencies {
    implementation(*Libs.bundle.kotlinSerialization)
}
