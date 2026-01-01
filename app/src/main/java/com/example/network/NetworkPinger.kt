package com.example.network

import java.io.BufferedReader
import java.io.InputStreamReader

class NetworkPinger {
    fun getLatency(url: String): Long {
        val cleanUrl = url.replace("https://", "")
            .replace("http://", "")
            .replace("www.", "")
            .split("/")[0]

        return try {
            val process = Runtime.getRuntime().exec("ping -c 1 -w 2 $cleanUrl")
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String?
            var latency = -1L

            while (reader.readLine().also { line = it } != null) {
                if (line!!.contains("time=")) {
                    val parts = line!!.split("time=")
                    if (parts.size > 1) {
                        val timeString = parts[1].split(" ")[0]
                        latency = timeString.toDouble().toLong()
                    }
                }
            }

            process.waitFor()
            latency
        } catch (e: Exception) {
            e.printStackTrace()
            -1L
        }
    }
}