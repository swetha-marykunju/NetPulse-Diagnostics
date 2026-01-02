[![Android CI](https://github.com/swetha-marykunju/NetPulse-Diagnostics/actions/workflows/android.yml/badge.svg)](https://github.com/swetha-marykunju/NetPulse-Diagnostics/actions/workflows/android.yml)

Network Diagnostics

A high performance Android utility that provides real-time network telemetry, combining low-level system pings with high level API metadata.

The Challenge

Most network tools only show if you are connected. This app dives deeper, correlating physical latency (ICMP) with geographic and ISP data to give a full picture of network quality.

Technical Implementation
- Architecture: MVVM (Model-View-ViewModel) for a clean separation of concerns.
- Networking: Layer 3: Raw Shell commands to calculate ICMP packet latency.
- Layer 7: Retrofit + GSON to fetch ISP and Geo-location data from REST APIs.
- Persistence: Room Database with Coroutines to store history without blocking the UI thread.
- Reactive UI: LiveData observers ensure the UI stays in sync with background network tasks.

Key Libraries
- Retrofit2 & Converter-GSON
- androidx.room (Room)
- kotlinx-coroutines-android
- androidx.lifecycle (ViewModel, LiveData)

How to Run
1. Clone the repository.
2. Open in Android Studio (Ladybug or newer).
3. Ensure your device has internet access.
4. Run the 'app' module.
