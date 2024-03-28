package com.ingecorp.camilo_zavala_herpm13051_s8

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ingecorp.camilo_zavala_herpm13051_s8.utils.ConverterUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorUI()
        }
    }
}

@Composable
fun CalculatorUI() {
    val context = LocalContext.current
    var decimalInput by remember { mutableStateOf("") }
    var binaryInput by remember { mutableStateOf("") }
    var decimalResult by remember { mutableStateOf("") }
    var binaryResult by remember { mutableStateOf("") }
    var secondDecimalInput by remember { mutableStateOf("") }
    var secondBinaryInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = decimalInput,
            onValueChange = { decimalInput = it },
            label = { Text("Decimal Input") }
        )
        OutlinedTextField(
            value = binaryInput,
            onValueChange = { binaryInput = it },
            label = { Text("Binary Input") }
        )
        OutlinedTextField(
            value = secondDecimalInput,
            onValueChange = { secondDecimalInput = it },
            label = { Text("Second Decimal Input") }
        )
        OutlinedTextField(
            value = secondBinaryInput,
            onValueChange = { secondBinaryInput = it },
            label = { Text("Second Binary Input") }
        )

        Button(onClick = {
            if (decimalInput.isNotEmpty() && decimalInput.matches("-?\\d+(\\.\\d+)?".toRegex())) {
                decimalResult = ConverterUtils.decimalToBinary(decimalInput.toInt())
            } else {
                Toast.makeText(context, "Please enter a valid decimal number.", Toast.LENGTH_LONG).show()
            }
        }) {
            Text("Convert Decimal to Binary")
        }

        Button(onClick = {
            if (binaryInput.isNotEmpty() && binaryInput.matches("[01]+".toRegex())) {
                binaryResult = ConverterUtils.binaryToDecimal(binaryInput).toString()
            } else {
                Toast.makeText(context, "Please enter a valid binary number.", Toast.LENGTH_LONG).show()
            }
        }) {
            Text("Convert Binary to Decimal")
        }

        Button(onClick = {
            if (decimalInput.isNotEmpty() && secondDecimalInput.isNotEmpty() && decimalInput.matches("-?\\d+".toRegex()) && secondDecimalInput.matches("-?\\d+".toRegex())) {
                val sum = ConverterUtils.sumDecimal(decimalInput.toInt(), secondDecimalInput.toInt())
                decimalResult = "Sum: $sum"
            } else {
                Toast.makeText(context, "Please enter valid decimal numbers for sum.", Toast.LENGTH_LONG).show()
            }
        }) {
            Text("Suma Decimals")
        }

        Button(onClick = {
            if (binaryInput.isNotEmpty() && secondBinaryInput.isNotEmpty() && binaryInput.matches("[01]+".toRegex()) && secondBinaryInput.matches("[01]+".toRegex())) {
                val sum = ConverterUtils.sumBinary(binaryInput, secondBinaryInput)
                binaryResult = "Sum: $sum"
            } else {
                Toast.makeText(context, "Please enter valid binary numbers for sum.", Toast.LENGTH_LONG).show()
            }
        }) {
            Text("Suma Binary")
        }

        Button(onClick = {
            if (decimalInput.isNotEmpty() && secondDecimalInput.isNotEmpty() && decimalInput.matches("-?\\d+".toRegex()) && secondDecimalInput.matches("-?\\d+".toRegex())) {
                val difference = ConverterUtils.subtractDecimal(decimalInput.toInt(), secondDecimalInput.toInt())
                decimalResult = "Resta Decimal: $difference"
            } else {
                Toast.makeText(context, "Por favor, ingresa números decimales válidos para la resta.", Toast.LENGTH_LONG).show()
            }
        }) {
            Text("Restar Decimales")
        }

        Button(onClick = {
            if (binaryInput.isNotEmpty() && secondBinaryInput.isNotEmpty() && binaryInput.matches("[01]+".toRegex()) && secondBinaryInput.matches("[01]+".toRegex())) {
                val difference = ConverterUtils.subtractBinary(binaryInput, secondBinaryInput)
                binaryResult = "Resta Binaria: $difference"
            } else {
                Toast.makeText(context, "Por favor, ingresa números binarios válidos para la resta.", Toast.LENGTH_LONG).show()
            }
        }) {
            Text("Restar Binarios")
        }

        Text("Decimal Result: $decimalResult")
        Text("Binary Result: $binaryResult")
    }
}
