# NetPulse Diagnostics

[![Android CI](https://github.com/swetha-marykunju/NetPulse-Diagnostics/actions/workflows/android.yml/badge.svg)](https://github.com/swetha-marykunju/NetPulse-Diagnostics/actions/workflows/android.yml)

A high-performance Android utility providing real-time network telemetry, combining low-level system pings with high-level ISP metadata.

## The Challenge
Most network tools only show if you are connected. NetPulse correlates physical latency (ICMP) with geographic and ISP data to provide a comprehensive picture of network quality and history.

## Technical Implementation
- **UI Architecture:** Built entirely with **Jetpack Compose** (Declarative UI), utilizing a single-activity architecture for a modern, fluid user experience.
- **Pattern:** **MVVM** (Model-View-ViewModel) to ensure clean separation of concerns and testability.
- **Networking:** - **Layer 3:** Raw Shell commands to calculate ICMP packet latency.
    - **Layer 7:** Retrofit + GSON to fetch ISP and Geo-location data from REST APIs.
- **Persistence:** **Room Database** with Kotlin Coroutines for offline storage and historical data tracking.
- **Quality Assurance:** Integrated **JUnit 4** for unit testing business logic and **GitHub Actions** for CI/CD automation.

## Key Libraries
- **Jetpack Compose** (Material 3)
- **Retrofit2** & Converter-GSON
- **AndroidX Room** (Persistence)
- **Kotlin Coroutines** (Asynchronous operations)
- **AndroidX Lifecycle** (ViewModel, StateFlow/LiveData)

## How to Run
1. Clone the repository.
2. Open in Android Studio (Ladybug or newer).
3. Ensure your device has internet access.
4. Build and run the 'app' module.
