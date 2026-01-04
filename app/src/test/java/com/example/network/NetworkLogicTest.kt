package com.example.network

import junit.framework.TestCase
import org.junit.Test

class NetworkLogicTest {

    @Test
    fun testPingResultDataIntegrity() {
        val result = PingResult(
            id = 1,
            url = "https://google.com",
            latency = 45L,
            time = "12:00:01",
            isSuccess = true,
            networkType = "Wifi"
        )

        TestCase.assertEquals("https://google.com", result.url)
        TestCase.assertEquals(45L, result.latency)
        TestCase.assertTrue(result.isSuccess)
        TestCase.assertEquals("Wifi", result.networkType)
    }

    @Test
    fun testLatencyColorLogicThresholds() {
        fun getColorForLatency(latency: Long): String {
            return when {
                latency == -1L -> "GRAY"
                latency < 60 -> "GREEN"
                latency < 150 -> "YELLOW"
                else -> "RED"
            }
        }

        TestCase.assertEquals("GREEN", getColorForLatency(30))
        TestCase.assertEquals("YELLOW", getColorForLatency(100))
        TestCase.assertEquals("RED", getColorForLatency(200))
        TestCase.assertEquals("GRAY", getColorForLatency(-1))
    }
}