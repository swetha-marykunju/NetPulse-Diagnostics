package com.example.network

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class NetworkLogicTest {

    @Test
    fun testPingResultDataIntegrity() {
        val result = PingResult(
            url = "https://google.com",
            latency = 45L,
            time = "12:00:01",
            isSuccess = true,
            networkType = "Wifi"
        )

        assertEquals("https://google.com", result.url)
        assertEquals(45L, result.latency)
        assertTrue(result.isSuccess)
        assertEquals("Wifi", result.networkType)
    }

    @Test
    fun testLatencyColorLogicThresholds() {
        fun getColorForLatency(latency: Long): String {
            return when {
                latency == -1L -> "BLACK"
                latency < 60 -> "GREEN"
                latency < 150 -> "YELLOW"
                else -> "RED"
            }
        }

        assertEquals("GREEN", getColorForLatency(30))
        assertEquals("YELLOW", getColorForLatency(100))
        assertEquals("RED", getColorForLatency(200))
        assertEquals("BLACK", getColorForLatency(-1))
    }
}