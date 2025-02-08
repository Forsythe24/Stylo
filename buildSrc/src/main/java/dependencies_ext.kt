import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.kapt(vararg list: String) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.annotationProcessor(vararg list: String) {
    list.forEach { dependency ->
        add("annotationProcessor", dependency)
    }
}

fun DependencyHandler.implementation(vararg list: Any) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.api(vararg list: String) {
    list.forEach { dependency ->
        add("api", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(vararg list: String) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(vararg list: Any) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.testRuntimeOnly(vararg list: Any) {
    list.forEach { dependency ->
        add("testRuntimeOnly", dependency)
    }
}

fun DependencyHandler.coreLibraryDesugaring(vararg list: String) {
    list.forEach { dependency ->
        add("coreLibraryDesugaring", dependency)
    }
}
