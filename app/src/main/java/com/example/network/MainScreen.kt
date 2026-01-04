package com.example.network

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(
    ispName: String,
    currentLatency: String,
    historyList: List<PingResult>,
    isChecking: Boolean,
    onTestClick: () -> Unit,
    onClearClick: () -> Unit
) {
    Scaffold(
        containerColor = Color(0xFFF8F9FA)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NetworkHeaderCard(isp = ispName, latency = currentLatency)

            Button(
                onClick = onTestClick,
                enabled = !isChecking,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3498DB),
                    disabledContainerColor = Color(0xFFBDC3C7)
                )
            ) {
                if (isChecking) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                    Spacer(Modifier.width(12.dp))
                    Text("Checking...")
                } else {
                    Text(
                        text = "Test Network Speed",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            TextButton(
                onClick = onClearClick,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = "Clear History",
                    color = Color(0xFFE74C3C),
                    fontWeight = FontWeight.SemiBold
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(historyList) { result ->
                    PingResultItem(result = result)

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        thickness = 0.5.dp,
                        color = Color(0xFFECF0F1)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val dummyHistory = listOf(
        PingResult(
            time = "14:02",
            url = "google.com",
            networkType = "Wi-Fi",
            latency = 222L,
            isSuccess = true
        ),
        PingResult(
            time = "14:05",
            url = "netflix.com",
            networkType = "Cellular",
            latency = 131L,
            isSuccess = true
        )
    )

    MainScreen(
        ispName = "Verizon Fios",
        currentLatency = "24",
        historyList = dummyHistory,
        isChecking = false,
        onTestClick = {},
        onClearClick = {}
    )
}