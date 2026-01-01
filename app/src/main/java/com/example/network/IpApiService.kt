package com.example.network

import retrofit2.http.GET

interface IpApiService {
    @GET("json")
    suspend fun getIpDetails(): IpInfo
}