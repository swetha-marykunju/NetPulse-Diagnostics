package com.example.network

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ping_history")
data class PingResult(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val url: String,
    val latency: Long,
    val time: String,
    val networkType: String,
    val isSuccess: Boolean
)