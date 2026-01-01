package com.example.network

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PingDao {
    @Query("SELECT * FROM ping_history ORDER BY id DESC LIMIT 20")
    fun getAllPings(): Flow<List<PingResult>>
    @Insert
    suspend fun insertPing(ping: PingResult)

    @Query("DELETE FROM ping_history")
    suspend fun clearHistory()

    @Query("DELETE FROM ping_history")
    suspend fun deleteAll()
}