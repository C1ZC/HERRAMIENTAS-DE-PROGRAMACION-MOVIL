package com.example.camilo_zavala_herpm1305_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import com.example.camilo_zavala_herpm1305_1.ui.theme.Camilo_zavala_HERPM13051Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Camilo_zavala_HERPM13051Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FoodOrderScreen()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Registro de información en la consola al cerrar la aplicación
        Log.d("MainActivity", "La aplicación se ha cerrado.")
    }
}

@Composable
fun FoodOrderScreen() {
    var foodName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var deliveryAddress by remember { mutableStateOf("") }
    var isDialogVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campos de entrada para el pedido de comida
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            OutlinedTextField(
                value = foodName,
                onValueChange = { foodName = it },
                label = { Text("Nombre de la Comida") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Cantidad") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = deliveryAddress,
                onValueChange = { deliveryAddress = it },
                label = { Text("Dirección de Entrega") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para mostrar la información del pedido
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Button(
                onClick = {
                    displayOrderInfo(foodName, quantity, deliveryAddress)
                    isDialogVisible = true
                },
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text("Realizar Pedido")
            }
        }

        // Mostrar el diálogo si isDialogVisible es verdadero
        if (isDialogVisible) {
            OrderInfoDialog(
                foodName = foodName,
                quantity = quantity,
                deliveryAddress = deliveryAddress,
                onDismiss = { isDialogVisible = false }
            )
        }
    }
}


fun displayOrderInfo(foodName: String, quantity: String, deliveryAddress: String) {
    Log.d("OrderInfo", "Nombre De La Comida: $foodName, Cantidad: $quantity, Dirección De Entrega: $deliveryAddress")
    // Mostrar información del pedido en la consola
    println("Información De La Orden:")
    println("Nombre De La Comida: $foodName")
    println("Cantidad: $quantity")
    println("Dirección De Entrega: $deliveryAddress")
}

@Composable
fun OrderInfoDialog(foodName: String, quantity: String, deliveryAddress: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Información De La Orden") },
        text = {
            Column {
                Text("Nombre De La Comida: $foodName")
                Text("Cantidad: $quantity")
                Text("Dirección De Entrega: $deliveryAddress")
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onDismiss() },
                colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("OK")
            }
        }
    )
}
