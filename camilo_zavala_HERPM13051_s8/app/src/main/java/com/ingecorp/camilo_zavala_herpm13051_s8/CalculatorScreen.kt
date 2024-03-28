package com.ingecorp.camilo_zavala_herpm13051_s8.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ingecorp.camilo_zavala_herpm13051_s8.utils.ConverterUtils

@Composable
fun CalculatorScreen() {
    var decimalValue by remember { mutableStateOf("") }
    var binaryValue by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Decimal to Binary & Binary to Decimal Converter", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = decimalValue,
            onValueChange = { decimalValue = it },
            label = { Text("Enter Decimal Number") },
            singleLine = true
        )
        Button(onClick = { result = ConverterUtils.decimalToBinary(decimalValue.toIntOrNull() ?: 0) }) {
            Text("Convert to Binary")
        }
        OutlinedTextField(
            value = binaryValue,
            onValueChange = { binaryValue = it },
            label = { Text("Enter Binary Number") },
            singleLine = true
        )
        Button(onClick = { result = ConverterUtils.binaryToDecimal(binaryValue).toString() }) {
            Text("Convert to Decimal")
        }
        Text("Result: $result", style = MaterialTheme.typography.bodyLarge)
    }
}
