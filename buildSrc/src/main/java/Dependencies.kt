import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.example.dictionary"
    const val compile_sdk = 31
    const val min_sdk = 23
    const val target_sdk = 31
    val java_version = JavaVersion.VERSION_1_8
    const val jvm_target = "1.8"
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
    const val historyScreen = ":historyScreen"
}

object Versions {
    const val roomVersion = "2.4.2"
    const val glideVersion = "4.11.0"
    const val picassoVersion = "2.71828"
    const val coilVersion = "1.3.2"
    const val koinVersion = "3.1.2"
    const val lifecycleVersion = "2.4.1"
    const val kotlinVersion = "1.7.0"
    const val coroutinesVersion = "1.5.0"
    const val retrofit2Version = "2.9.0"
    const val loggingInterceptorVersion = "5.0.0-alpha.3"
    const val materialVersion = "1.5.0"
    const val swipeRefreshLayoutVersion = "1.1.0"
    const val appcompatVersion = "1.4.1"
    const val activityKTXVersion = "1.4.0"
    const val fragmentKTXVersion = "1.4.1"
    const val junitVersion = "4.13.2"
    const val extJunitVersion = "1.1.3"
    const val espressoCoreVersion = "3.4.0"
}

object Room {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-common:${Versions.roomVersion}"
    const val roomKTX = "androidx.room:room-ktx:${Versions.roomVersion}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
}

object Picasso {
    const val picasso = "com.squareup.picasso:picasso:${Versions.picassoVersion}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coilVersion}"
}

object Koin {
    const val koinCore = "io.insert-koin:koin-core:${Versions.koinVersion}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinVersion}"
    const val koinAndroidCompat = "io.insert-koin:koin-android-compat:${Versions.koinVersion}"
}

object Lifecycle {
    const val lifecycleViewModelKTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val lifecycleLiveDataKTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    const val lifecycleViewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycleVersion}"
}

object KotlinCore {
    const val kotlinCore = "androidx.core:core-ktx:${Versions.kotlinVersion}"
    const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
}

object Retrofit2 {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2Version}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2Version}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptorVersion}"
}

object Design {
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayoutVersion}"
}

object Androidx {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompatVersion}"
    const val activityKTX = "androidx.activity:activity-ktx:${Versions.activityKTXVersion}"
    const val fragmentKTX = "androidx.fragment:fragment-ktx:${Versions.fragmentKTXVersion}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunitVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
}