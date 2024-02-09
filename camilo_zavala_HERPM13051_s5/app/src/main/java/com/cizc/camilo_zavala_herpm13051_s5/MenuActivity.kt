package com.cizc.camilo_zavala_herpm13051_s5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val buttonVendedores = findViewById<Button>(R.id.btnVendedores) // Asegúrate de que el ID sea correcto
        buttonVendedores.setOnClickListener {
            val intent = Intent(this, VendedoresActivity::class.java)
            startActivity(intent)
        }
        val buttonCamara = findViewById<Button>(R.id.btnCamara) // Asegúrate de que el ID sea correcto
        buttonCamara.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        // Repite este proceso para otros botones y actividades en tu menú
    }
}
