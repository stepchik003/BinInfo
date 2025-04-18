package com.example.bininfo.presentation.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bininfo.domain.model.BinInfo
import com.example.bininfo.util.dialPhone
import com.example.bininfo.util.openMap
import com.example.bininfo.util.openUrl

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadHistory()
    }

    when (val result = state) {
        is HistoryUiState.Loading -> CircularProgressIndicator(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))
        is HistoryUiState.Error -> Text("Error: ${result.message}")
        is HistoryUiState.Success -> {
            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(result.history) { info ->
                    BinItem(info)
                }
            }
        }
    }
}

@Composable
fun BinItem(info: BinInfo) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            InfoRow("BIN", info.bin)
            InfoRow("Scheme", info.scheme)
            InfoRow("Type", info.type)
            InfoRow("Brand", info.brand)

            info.country?.let {
                val coordinates = "${info.latitude},${info.longitude}"
                ClickableInfoRow(
                    label = "Country",
                    value = "$it (${info.latitude?.toInt()}, ${info.longitude?.toInt()})"
                ) {
                    openMap(context, coordinates)
                }
            }

            info.bankName?.let { InfoRow("Bank", it) }
            info.city?.let { InfoRow("City", it) }
            info.phone?.let { phone ->
                ClickableInfoRow("Phone", phone) {
                    dialPhone(context, phone)
                }
            }

            info.url?.let { url ->
                ClickableInfoRow("URL", url) {
                    openUrl(context, url)
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String?) {
    if (!value.isNullOrBlank()) {
        Row(modifier = Modifier.padding(vertical = 2.dp)) {
            Text(
                text = "$label:",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(2f)
            )
        }
    }
}

@Composable
fun ClickableInfoRow(label: String, value: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 2.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
            modifier = Modifier.weight(2f)
        )
    }
}