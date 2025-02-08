
plugins {
    id(Libs.plugin.application)
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
    id(Libs.plugin.detekt)
    id(Libs.plugin.google_services)
    id(Libs.plugin.crashlytics)
}

common()
compose()
dagger()
lifecycle()
paging()

android {
    namespace = "com.solopov.stylo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.solopov.stylo"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_20
        targetCompatibility = JavaVersion.VERSION_20
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "20"
    }
}

dependencies {
    implementation(*Libs.bundle.firebase)
    implementation(libs.firebase.analytics)

    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)

    implementation(projects.featureAuthApi)
    implementation(projects.featureAuthImpl)
    implementation(projects.featureCartApi)
    implementation(projects.featureCartImpl)
    implementation(projects.featureSearchApi)
    implementation(projects.featureSearchImpl)
    implementation(projects.featureHomeApi)
    implementation(projects.featureHomeImpl)
    implementation(projects.featureFavoritesApi)
    implementation(projects.featureFavoritesImpl)
    implementation(projects.featureBottomBar)
}

detekt {
    toolVersion = Libs.detektVersion
    val configFile = File(project.rootDir, "config/detekt/detekt.yml")
    config.setFrom(configFile)
    buildUponDefaultConfig = true
}
