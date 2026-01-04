package com.example.network

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: NetworkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(this)
        val repository = NetworkRepository(database.pingDao())
        val factory = NetworkViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, factory)[NetworkViewModel::class.java]

        setContent {
            val ispInfo by viewModel.ispResult.observeAsState("Not Detected")
            val latency by viewModel.latencyResult.observeAsState(-1L)
            val history by viewModel.historyLog.observeAsState(emptyList())
            val isChecking by viewModel.isChecking.observeAsState(false)

            val latencyDisplay = if (latency != -1L) "$latency" else "--"
            MainScreen(
                ispName = ispInfo ?: "Not Detected",
                currentLatency = latencyDisplay,

                historyList = history ?: emptyList(),
                isChecking = isChecking,
                onTestClick = { viewModel.performPing("8.8.8.8") },
                onClearClick = { viewModel.clearHistory() }
            )
        }
    }
}