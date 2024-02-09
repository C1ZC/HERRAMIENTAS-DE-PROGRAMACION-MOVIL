package com.cizc.camilo_zavala_herpm13051_s5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cizc.camilo_zavala_herpm13051_s5.adapter.VendedorAdapter
import com.cizc.camilo_zavala_herpm13051_s5.adapter.Vendedor

class VendedoresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendedores)

        // Lista de cinco vendedores como ejemplo
        val listaVendedores = listOf(
            Vendedor("Vendedor 1", "url/foto1.jpg", "Área 1"),
            Vendedor("Vendedor 2", "url/foto2.jpg", "Área 2"),
            Vendedor("Vendedor 3", "url/foto3.jpg", "Área 3"),
            Vendedor("Vendedor 4", "url/foto4.jpg", "Área 4"),
            Vendedor("Vendedor 5", "url/foto5.jpg", "Área 5")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewVendedores)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = VendedorAdapter(listaVendedores) { index ->
            if (index == 2) { // Si se selecciona el tercer vendedor (index 2)
                val intent = Intent(this, AudioActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
