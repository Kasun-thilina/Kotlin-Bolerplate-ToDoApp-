/**
 * Created by Kasun Thilina on 03/8/2020
 */

object ApplicationId {
    const val id = "com.kc.todoapp"
}

object Modules {
    const val core = ":core"
    const val app = ":app"
    const val navigation = ":navigation"

    const val presentation = ":common:presentation"
    const val api = ":common:api"
    const val network = ":common:network"
    const val apiclient = ":apiclient"

    const val splash = ":features:splash"
    const val authentication = ":features:authentication"
    const val notifications = ":features:notifications"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29

    const val gradle = "3.5.3"

    const val kotlin = "1.3.61"
    const val ktx = "1.3.2"
    const val koin = "2.0.1"
    const val rxjava = "2.2.6"
    const val rxkotlin = "2.3.0"
    const val retrofit = "2.6.0"
    const val loggingInterceptor = "3.12.1"
    const val retrofitJson = "2.4.0"

    const val sdkVersion = "28.0.0"
    const val appcompat = "1.0.2"
    const val design = "1.2.0-beta01"
    const val cardview = "1.0.0"
    const val recyclerview = "1.0.0"
    const val constraintlayout = "2.0.4"
    const val maps = "15.0.1"
    const val lifecycle = "2.0.0"
    const val multidex = "2.0.1"
    const val support = "1.0.0"

    const val playCore = "1.3.7"
    const val googleAuth = "16.0.1"
    const val googleServices = "4.2.0"

    const val room = "2.0.0"

    const val firebaseAuth = "17.2.1"
    const val firebaseCore = "17.2.1"
    const val firebaseMessaging = "20.1.0"
    const val firebaseCrashlytics = "2.10.1"
    const val firebaseAnalytics = "17.2.2"
    const val crashlytics = "2.9.9"

    const val timber = "4.7.1"
    const val glide = "4.9.0"

    const val volley = "1.1.0"
    const val gson = "2.8.5"
    const val okhttp = "3.10.0"

    const val butterKnife = "10.1.0"
    const val anko = "0.10.8"

    const val moshi = "1.8.0"
    const val threetenabp = "1.1.1"
    const val viewPager2 = "1.0.0"

}

object Libraries {
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val volley = "com.android.volley:volley:${Versions.volley}"

    const val butterKnife = "com.jakewharton:butterknife:${Versions.butterKnife}"
    const val butterKnifeCompiler = "com.jakewharton:butterknife-compiler:${Versions.butterKnife}"

    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"

    //  https://github.com/Kotlin/anko
    const val anko = "org.jetbrains.anko:anko:${Versions.anko}"

    const val threetenabp = "com.jakewharton.threetenabp:threetenabp:${Versions.threetenabp}"
}

object Kotlin {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Androidx {

    const val gredle_plugin = "com.android.tools.build:gradle:3.2.1"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val design = "com.google.android.material:material:${Versions.design}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val legacysupport = "androidx.legacy:legacy-support-v4:${Versions.support}"
    const val mulidex = "androidx.multidex:multidex:${Versions.multidex}"
    const val constraintlayout =
        "com.android.support.constraint:constraint-layout:${Versions.constraintlayout}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val rxjava = "androidx.room:room-rxjava2:${Versions.room}"
}

object Lifecycle {
    const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
}

object Googlelibraries {
    const val maps = "com.google.android.gms:play-services-maps:${Versions.maps}"
    const val place = "com.google.android.Libraries.places:places:2.0.0"
    const val location = "com.google.android.gms:play-services-location:${Versions.maps}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object Firebaselibraries {
    val auth = "com.google.firebase:firebase-auth:${Versions.firebaseAuth}"
    val core = "com.google.firebase:firebase-core:${Versions.firebaseCore}"
    val database_ui = "com.firebaseui:firebase-ui-database:6.0.2"
}

object Moshi {
    const val runtime = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val adapter = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
    const val kotlin = "com.squareup.moshi:moshi-Kotlin:${Versions.moshi}"
}

object Externallib {
    const val loader = "com.github.ybq:Android-SpinKit:1.4.0"
    const val countryPick = "com.hbb20:ccp:2.3.1"
    const val facebookSdk = "com.facebook.android:facebook-login:[5,6)"
    const val exoplayer = "com.google.android.exoplayer:exoplayer:2.9.5"
    const val imagepicker = "com.github.dhaval2404:imagepicker:1.3"
    const val cropper = "com.isseiaoki:simplecropview:1.1.8"
    const val imageSelecter = "com.github.LuckSiege.PictureSelector:picture_library:v2.2.4"


    const val rate = "me.zhanghai.android.materialratingbar:library:1.3.2"
    const val emoji = "com.vanniktech:emoji-google:0.6.0"

    const val filePicker = "com.droidninja:filepicker:2.2.1"
    const val imageViwer = "com.github.stfalcon:stfalcon-imageviewer:0.1.0"
}

object Apiliveserver {
    const val url = "\"\""
    const val apiKey = "\"\""
}

object Apisandboxserver {
    const val url = "\"https://newsapi.org/v2/\""
    const val apiKey = "\"55d61398b4ba44d7949d20923e0f0bc9\""
}

object Retrofit {
    const val runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitJson}"
    const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
}

object Okhttp {
    const val runtime = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
}


object Glide {
    const val runtime = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Reactivex {
    const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    const val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"
}

object Koin {
    const val android = "org.koin:koin-android:${Versions.koin}"
    const val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val scope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val ext = "org.koin:koin-android-ext:${Versions.koin}"

}

