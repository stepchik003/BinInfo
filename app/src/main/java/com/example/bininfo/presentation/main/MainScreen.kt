package com.example.bininfo.presentation.main

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bininfo.domain.model.BinInfo
import com.example.bininfo.util.dialPhone
import com.example.bininfo.util.openMap
import com.example.bininfo.util.openUrl

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var input by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp),
            value = input,
            onValueChange = {input = it},
            label = {Text("Введите BIN (6-8 цифр)")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done),

        )
        Spacer(Modifier.height(24.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {viewModel.searchBin(input)}
        ) {
            Text("Искать")
        }
        Spacer(Modifier.height(24.dp))

        when (val state = uiState) {
            is MainUiState.Idle -> Unit
            is MainUiState.Loading -> CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
            is MainUiState.Success -> BinInfoCard(state.binInfo)
            is MainUiState.Error -> Toast.makeText(LocalContext.current, state.message, Toast.LENGTH_SHORT).show()
        }

    }

}

@Composable
fun BinInfoCard(info: BinInfo) {
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
