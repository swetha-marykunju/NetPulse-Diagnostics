package com.example.network

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NetworkViewModel
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mainView = findViewById<View>(R.id.main)
        mainView?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        val latencyText = findViewById<TextView>(R.id.latencyValueText)
        val ispText = findViewById<TextView>(R.id.ispTextView)
        val indicator = findViewById<View>(R.id.statusIndicator)
        val checkButton = findViewById<Button>(R.id.checkButton)
        val clearButton = findViewById<Button>(R.id.clearButton)
        val recyclerView = findViewById<RecyclerView>(R.id.historyRecyclerView)

        adapter = HistoryAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val database = AppDatabase.getDatabase(this)
        val repository = NetworkRepository(database.pingDao())
        val factory = NetworkViewModelFactory(application, repository)

        viewModel = ViewModelProvider(this, factory)[NetworkViewModel::class.java]

        viewModel.ispResult.observe(this) { info ->
            ispText.text = info
        }

        viewModel.latencyResult.observe(this) { result ->
            latencyText.text = if (result != -1L) "$result" else "--"

            when {
                result == -1L -> indicator.setBackgroundColor(Color.BLACK)
                result < 60 -> {
                    indicator.setBackgroundColor(Color.parseColor("#4CAF50"))
                    latencyText.setTextColor(Color.parseColor("#4CAF50"))
                }
                result < 150 -> {
                    indicator.setBackgroundColor(Color.parseColor("#FFEB3B"))
                    latencyText.setTextColor(Color.parseColor("#FBC02D"))
                }
                else -> {
                    indicator.setBackgroundColor(Color.parseColor("#F44336"))
                    latencyText.setTextColor(Color.parseColor("#F44336"))
                }
            }
        }

        viewModel.historyLog.observe(this) { newList ->
            adapter.updateData(newList ?: emptyList())
        }

        viewModel.isChecking.observe(this) { isChecking ->
            checkButton.isEnabled = !isChecking
            checkButton.text = if (isChecking) "Checking..." else "Test Network Speed"
        }

        checkButton.setOnClickListener {
            viewModel.performPing("8.8.8.8")
        }

        clearButton.setOnClickListener {
            viewModel.clearHistory()
        }
    }
}