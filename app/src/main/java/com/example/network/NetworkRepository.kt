package com.example.network

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NetworkRepository(private val pingDao: PingDao) {
    private val pinger = NetworkPinger()

    val allPings = pingDao.getAllPings()

    suspend fun performAndSavePing(context: Context, url: String): Long {
        val latency = pinger.getLatency(url)
        val type = NetworkUtils.getCurrentNetworkType(context)

        val result = PingResult(
            url = url,
            latency = latency,
            time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date()),
            networkType = type,
            isSuccess = latency != -1L
        )

        pingDao.insertPing(result)
        return latency
    }

    suspend fun clearAllHistory() {
        pingDao.deleteAll()
    }

    private val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl("http://ip-api.com/")
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build()

    private val ipApi = retrofit.create(IpApiService::class.java)

    suspend fun getNetworkDetails(): IpInfo? {
        return try {
            ipApi.getIpDetails()
        } catch (e: Exception) {
            null
        }
    }
}