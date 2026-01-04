package com.example.network

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
fun NetworkHeaderCard(isp: String, latency: String) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "CURRENT NETWORK",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7F8C8D)
            )

            Text(
                text = isp,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2C3E50),
                modifier = Modifier.padding(top = 4.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                thickness = 1.dp,
                color = Color(0xFFECF0F1)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = latency,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3498DB)
                )
                Text(
                    text = "Latency (ms)",
                    fontSize = 12.sp,
                    color = Color(0xFF95A5A6)
                )

                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(12.dp)
                        .background(
                            color = if (latency == "--") Color.LightGray else Color(0xFF2ECC71),
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Composable
fun PingResultItem(result: PingResult) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = result.time,
            fontSize = 12.sp,
            color = Color(0xFF7F8C8D)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = result.url,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2C3E50)
            )
            Text(
                text = result.networkType,
                fontSize = 11.sp,
                color = if (result.networkType == "Wi-Fi") Color(0xFF3498DB) else Color(0xFFE67E22)
            )
        }

        val latencyColor = when {
            !result.isSuccess || result.latency == -1L -> Color.Gray
            result.latency < 60 -> Color(0xFF4CAF50)
            result.latency < 150 -> Color(0xFFFBC02D)
            else -> Color(0xFFF44336)
        }

        Text(
            text = if (result.latency == -1L) "Err" else "${result.latency}ms",
            fontWeight = FontWeight.Bold,
            color = latencyColor
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF8F9FA)
@Composable
fun NetworkScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        NetworkHeaderCard(isp = "Starlink", latency = "42")

        Text(
            text = "HISTORY",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp),
            color = Color(0xFF95A5A6)
        )

        PingResultItem(
            PingResult(
                id = 1,
                url = "google.com",
                latency = 32L,
                time = "14:00",
                networkType = "Wi-Fi",
                isSuccess = true
            )
        )

        PingResultItem(
            PingResult(
                id = 2,
                url = "github.com",
                latency = 160L,
                time = "14:21",
                networkType = "Cellular",
                isSuccess = true
            )
        )
    }
}