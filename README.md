# Miixs Android

> 실시간 채팅 기반 소셜 네트워킹 안드로이드 앱

[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.0-7F52FF?logo=kotlin)](https://kotlinlang.org)
[![Android](https://img.shields.io/badge/Android-API%2024%2B-3DDC84?logo=android)](https://developer.android.com)
[![Min SDK](https://img.shields.io/badge/Min%20SDK-24-blue)](https://developer.android.com/about/versions/nougat)
[![Target SDK](https://img.shields.io/badge/Target%20SDK-35-blue)](https://developer.android.com/about/versions/15)
[![License](https://img.shields.io/badge/License-Private-red)](#)



## 소개

**Miixs**는 위치 기반 사용자 탐색, 실시간 1:1 채팅, 소셜 포스트 기능을 제공하는 Android 네이티브 소셜 네트워킹 앱입니다. MVVM 아키텍처와 STOMP WebSocket을 활용해 실시간 메시지 전달을 구현했습니다.

- **패키지명:** `com.atolow.miixs`
- **최신 버전:** 1.3.9 (versionCode 39)
- **지원 OS:** Android 7.0 (API 24) 이상

---

## MVP 핵심 기능

### 1. 인증 시스템
- 이메일/비밀번호 로그인 및 회원가입
- 이메일 인증 코드 발송 및 검증
- 휴대폰 번호 SMS 인증
- OAuth 소셜 로그인 (추가 정보 입력 플로우 포함)
- JWT 기반 인증 + 자동 토큰 갱신 (401 인터셉터)
- 비밀번호 찾기 / 재설정 / 변경
- 계정 비활성화

### 2. 사용자 프로필 & 탐색
- 프로필 조회 및 편집 (멀티 이미지 업로드)
- 위치 / 성별 / 나이 필터 기반 사용자 탐색
- 사용자 대시보드 (통계 정보)
- 다른 사용자 프로필 보기
- 채팅 설정 (수신 조건 설정)

### 3. 실시간 채팅
- 1:1 채팅방 생성 및 관리
- 텍스트 메시지 전송 (REST API)
- 이미지 메시지 전송 (파일 업로드)
- **STOMP WebSocket**을 통한 실시간 메시지 수신
- 채팅방 즐겨찾기
- 읽지 않은 메시지 카운트 (룸별 / 전체)
- 메시지 읽음 처리
- 채팅방 나가기
- 백그라운드 알림 (Foreground Service)

### 4. 소셜 포스트
- 포스트 작성, 조회, 수정, 삭제
- 위치 / 성별 / 나이 필터 기반 포스트 피드
- 내 포스트 목록 관리
- 무한 스크롤 페이지네이션

### 5. 결제 및 구독
- Google Play Billing 인앱 결제 (Google Play 검증 서버 연동)
- 상품 목록 조회
- 결제 내역 및 거래 상세 조회

### 6. 안전 및 신고
- 사용자 차단 / 차단 해제 / 차단 목록
- 사용자 신고 / 포스트 신고
- 이용약관 / 개인정보처리방침 / 아동 안전 정책
- 전역 스크린샷 방지 (`FLAG_SECURE`)

---

## 기술 스택

| 분류 | 기술 | 버전 |
|------|------|------|
| **언어** | Kotlin | 2.1.0 |
| **빌드** | Android Gradle Plugin | 8.6.1 |
| **아키텍처** | MVVM + Repository Pattern | - |
| **비동기** | Kotlin Coroutines | 1.9.0 |
| **네트워크** | Retrofit + OkHttp | 2.9.0 / 4.12.0 |
| **실시간** | STOMP over WebSocket (NaikSoftware) | 1.6.6 |
| **Reactive** | RxJava 2 | 2.2.21 |
| **이미지** | Glide | 4.16.0 |
| **UI** | AndroidX + Material Design | 1.11.0 |
| **내비게이션** | Navigation Component | 2.7.6 |
| **UI 바인딩** | View Binding + Data Binding | - |
| **결제** | Google Play Billing | 8.3.0 |
| **로컬 저장소** | SharedPreferences | - |

---

## 아키텍처

```
┌─────────────────────────────────────────────┐
│                  UI Layer                    │
│         Activities / Fragments               │
│           (View Binding / XML)               │
└────────────────────┬────────────────────────┘
                     │ observes
┌────────────────────▼────────────────────────┐
│               ViewModel Layer                │
│        (LiveData / StateFlow / Coroutines)   │
└────────────────────┬────────────────────────┘
                     │ calls
┌────────────────────▼────────────────────────┐
│             Repository Layer                 │
│      (데이터 소스 추상화 / 단일 진입점)         │
└──────┬──────────────────────────────┬────────┘
       │                              │
┌──────▼──────┐              ┌────────▼────────┐
│ Network     │              │  Local Storage  │
│ (Retrofit)  │              │ (SharedPrefs)   │
│ (WebSocket) │              │  TokenManager   │
└─────────────┘              └─────────────────┘
```

**주요 패턴:**
- MVVM + Repository for clean separation of concerns
- Auth Interceptor for automatic token injection & 401 handling
- STOMP WebSocket with exponential backoff reconnection (최대 5회)
- Foreground Service for background notification delivery
- BroadcastReceiver for token expiration & ban events

---

## 프로젝트 구조

```
app/src/main/java/com/atolow/miixs/
│
├── MiixsApplication.kt          # Application entry point
│
├── data/
│   ├── billing/                 # Google Play BillingManager
│   ├── local/
│   │   ├── TokenManager.kt      # JWT 토큰 저장소
│   │   └── NotificationSettings.kt
│   ├── model/                   # Data classes (DTOs)
│   │   ├── auth/
│   │   ├── user/
│   │   ├── chat/
│   │   ├── post/
│   │   ├── payment/
│   │   ├── block/
│   │   ├── report/
│   │   └── notification/
│   ├── network/
│   │   ├── ApiClient.kt         # Retrofit 싱글턴
│   │   ├── AuthInterceptor.kt   # 토큰 자동 주입 / 401 처리
│   │   ├── AuthApi.kt
│   │   ├── UserApi.kt
│   │   ├── ChatApi.kt
│   │   ├── PostApi.kt
│   │   ├── PaymentApi.kt
│   │   ├── BlockApi.kt
│   │   └── ReportApi.kt
│   ├── repository/              # Repository 구현체
│   └── websocket/
│       └── WebSocketManager.kt  # STOMP WebSocket 관리
│
├── service/
│   └── ChatNotificationService.kt  # 백그라운드 알림 서비스
│
├── ui/
│   ├── common/
│   │   ├── BaseActivity.kt
│   │   └── BaseFragment.kt
│   ├── splash/
│   ├── auth/
│   ├── main/
│   ├── home/
│   ├── chat/
│   ├── post/
│   ├── profile/
│   └── payment/
│
└── util/
    ├── AuthUtil.kt
    ├── ChatNotificationManager.kt
    ├── ErrorHandler.kt
    ├── ImagePicker.kt
    ├── NetworkUtils.kt
    └── NotificationHelper.kt
```

---

## 시작하기

### 사전 요구사항

- Android Studio Hedgehog (2023.1.1) 이상
- JDK 17
- Android SDK (API 24 ~ 35)
- Kotlin 2.1.0


## API 개요

**Base URL:** `https://miixs.com/`
**WebSocket URL:** `wss://miixs.com/ws`
**인증 방식:** Bearer Token (JWT)

### 주요 엔드포인트

| 분류 | 메서드 | 경로 | 설명 |
|------|--------|------|------|
| **인증** | POST | `/api/auth/login` | 로그인 |
| | POST | `/api/auth/refresh` | 토큰 갱신 |
| | POST | `/api/auth/logout` | 로그아웃 |
| | POST | `/api/auth/verify-email` | 이메일 인증 |
| | POST | `/api/auth/phone/verify-code` | 전화번호 인증 |
| **사용자** | GET | `/api/users` | 사용자 탐색 (필터, 페이징) |
| | GET | `/api/users/{userId}` | 사용자 프로필 조회 |
| | GET | `/api/auth/dashboard` | 내 대시보드 |
| | POST | `/api/auth/profile/images` | 프로필 이미지 업로드 |
| **채팅** | GET | `/api/chat/rooms` | 채팅방 목록 |
| | POST | `/api/chat/rooms` | 채팅방 생성 |
| | GET | `/api/chat/rooms/{id}/messages` | 메시지 내역 |
| | POST | `/api/chat/messages` | 메시지 전송 |
| | GET | `/api/chat/unread-count/total` | 전체 미읽음 수 |
| **포스트** | GET | `/api/posts` | 포스트 피드 (필터, 페이징) |
| | POST | `/api/posts` | 포스트 작성 |
| | PUT | `/api/posts/{id}` | 포스트 수정 |
| | DELETE | `/api/posts/{id}` | 포스트 삭제 |
| **결제** | GET | `/api/payments/products` | 상품 목록 |
| | POST | `/api/payments/verify/google-play` | Google Play 검증 |
| | GET | `/api/payments` | 결제 내역 |
| **차단/신고** | POST | `/api/users/blocks` | 사용자 차단 |
| | POST | `/api/reports/users` | 사용자 신고 |

### WebSocket 채널 (STOMP)

| 채널 | 설명 |
|------|------|
| `/app/chat/rooms/{roomId}/messages` | 메시지 발송 |
| `/queue/chat/rooms/{roomId}` | 채팅방 메시지 구독 |
| `/user/{userId}/notifications` | 개인 알림 구독 |

---

## 보안

- **스크린샷 방지:** 앱 전체에 `FLAG_SECURE` 적용
- **HTTPS/WSS Only:** 모든 통신 암호화
- **JWT 자동 갱신:** 401 응답 시 토큰 재발급 후 재요청
- **파일 공유:** `FileProvider`를 통한 안전한 파일 URI 처리
- **토큰 저장:** SharedPreferences에 암호화된 형태로 저장
- **계정 정지 처리:** 서버 BroadcastReceiver를 통한 즉시 강제 로그아웃

---

## 개발 환경

| 항목 | 값 |
|------|-----|
| Language | Kotlin 2.1.0 |
| Android Gradle Plugin | 8.6.1 |
| Compile SDK | 35 |
| Min SDK | 24 (Android 7.0) |
| Target SDK | 35 (Android 15) |
| JVM Target | 17 |

---

## 라이선스

This project is private and proprietary. All rights reserved.

Copyright © 2024 Miixs. Unauthorized copying, distribution, or modification is strictly prohibited.
