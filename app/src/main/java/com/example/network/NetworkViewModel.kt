package com.example.network

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class NetworkViewModel(application: Application, private val repository: NetworkRepository)
    : AndroidViewModel(application) {

    val historyLog: LiveData<List<PingResult>> = repository.allPings.asLiveData()

    val latencyResult = MutableLiveData<Long>()
    val ispResult = MutableLiveData<String>()
    val isChecking = MutableLiveData<Boolean>()

    fun performPing(url: String) {
        viewModelScope.launch {
            isChecking.value = true
            ispResult.value = "Detecting ISP..."

            val ipInfo = repository.getNetworkDetails()
            if (ipInfo != null) {
                ispResult.value = "${ipInfo.isp} (${ipInfo.city})"
            } else {
                ispResult.value = "ISP Unknown"
            }

            val result = repository.performAndSavePing(getApplication(), url)
            latencyResult.value = result

            isChecking.value = false
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            repository.clearAllHistory()
        }
    }
}