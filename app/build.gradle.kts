plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

// keystore.properties 파일 로드
val keystorePropertiesFile = file("${projectDir}/keystore.properties")
val keystoreProperties = mutableMapOf<String, String>()
if (keystorePropertiesFile.exists()) {
    // UTF-8 인코딩으로 파일 읽기
    keystorePropertiesFile.readText(Charsets.UTF_8).split("\n").forEach { line ->
        val trimmedLine = line.trim()
        if (trimmedLine.contains("=") && !trimmedLine.startsWith("#") && trimmedLine.isNotEmpty()) {
            val parts = trimmedLine.split("=", limit = 2)
            if (parts.size == 2) {
                val key = parts[0].trim()
                val value = parts[1].trim()
                // 빈 값이 아닌 경우에만 추가
                if (key.isNotEmpty() && value.isNotEmpty()) {
                    keystoreProperties[key] = value
                }
            }
        }
    }
}

android {
    namespace = "com.atolow.miixs"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.atolow.miixs"
        minSdk = 24
        targetSdk = 35
        versionCode = 39
        versionName = "1.3.9"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // 서버 URL: 로컬 gradle.properties 또는 CI/CD 환경변수에서 주입
        // gradle.properties 에 BASE_URL=https://miixs.com/ 을 추가하거나
        // CI/CD 에서 -PBASE_URL=https://miixs.com/ 으로 전달
        val baseUrl = (project.findProperty("BASE_URL") as? String)
            ?: System.getenv("BASE_URL")
            ?: "https://miixs.com/"
        val wsUrl = (project.findProperty("WS_URL") as? String)
            ?: System.getenv("WS_URL")
            ?: "wss://miixs.com/ws"

        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        buildConfigField("String", "WS_URL", "\"$wsUrl\"")
    }

    // Signing 설정
    signingConfigs {
        create("release") {
            val storeFileProp = keystoreProperties["storeFile"]
            val storePasswordProp = keystoreProperties["storePassword"]
            val keyAliasProp = keystoreProperties["keyAlias"]
            val keyPasswordProp = keystoreProperties["keyPassword"] ?: storePasswordProp
            
            if (storeFileProp != null && storePasswordProp != null && keyAliasProp != null) {
                val keystoreFile = file("${projectDir}/${storeFileProp}")
                if (keystoreFile.exists()) {
                    storeFile = keystoreFile
                    storePassword = storePasswordProp
                    keyAlias = keyAliasProp
                    // keyPassword가 없으면 storePassword 사용
                    keyPassword = keyPasswordProp ?: storePasswordProp
                }
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true  // R8 활성화 (mapping 파일 생성)
            isShrinkResources = true  // 사용하지 않는 리소스 제거
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            // Google Play Billing 테스트를 위해 debug 빌드도 release keystore 사용
            signingConfig = signingConfigs.getByName("release")
        }
    }
    
    // AAB 설정 추가
    bundle {
        language {
            enableSplit = false  // 언어 분할 비활성화
        }
        density {
            enableSplit = true  // 밀도 분할 활성화
        }
        abi {
            enableSplit = true  // ABI 분할 활성화
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
    
    kapt {
        correctErrorTypes = true
        useBuildCache = true
        javacOptions {
            option("-Xmaxerrs", 500)
        }
    }
}

dependencies {
    // Android Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    
    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    
    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    
    // Retrofit & OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    
    // Coroutines (Kotlin 2.1.0 호환)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    
    // Image Loading
    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")
    
    // WebSocket (STOMP)
    implementation("com.github.NaikSoftware:StompProtocolAndroid:1.6.6")
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    
    // SharedPreferences
    implementation("androidx.preference:preference-ktx:1.2.1")
    
    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    
    // SwipeRefreshLayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    
    // CustomTabs for OAuth
    implementation("androidx.browser:browser:1.8.0")
    
    // Fragment
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    
    // Activity
    implementation("androidx.activity:activity-ktx:1.8.2")
    
    // Google Play Billing Library (7.0.0 이상 필요 - 2025년 8월 31일부터 필수)
    // 버전 8.3.0: 최신 수익 창출 기능 지원 (외부 결제, 다중 구매 옵션 등)
    implementation("com.android.billingclient:billing-ktx:8.3.0")
    
    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

