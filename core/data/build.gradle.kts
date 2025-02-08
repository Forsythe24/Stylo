
plugins {
    id(Libs.plugin.library)
}

common()
dagger()
room()

android {
    namespace = "com.solopov.coredata"
}


dependencies {
    api(*Libs.bundle.requests)
    api(*Libs.bundle.kotlinSerialization)

    implementation(Libs.bundle.datastore)
    implementation(*Libs.bundle.datastorePreferences)
}
