// Importación de paquetes necesarios
package com.camilo_zavala.basededatossqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

// Actividad principal de la aplicación
class MainActivity : AppCompatActivity() {

    // Método llamado al crear la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de la lista de productos como una lista vacía
        var listaProductos = emptyList<Producto>()

        // Obtención de la instancia de la base de datos Room
        val database = AppDatabase.getDatabase(this)

        // Observador que actualiza la lista de productos cuando hay cambios en la base de datos
        database.productos().getAll().observe(this, Observer {
            listaProductos = it

            // Creación de un adaptador para la lista de productos y asignación al ListView
            val adapter = ProductosAdapter(this, listaProductos)
            lista.adapter = adapter
        })

        // Manejador de clics en elementos de la lista
        lista.setOnItemClickListener { parent, view, position, id ->
            // Creación de un intent para abrir la actividad ProductoActivity con el ID del producto
            val intent = Intent(this, ProductoActivity::class.java)
            intent.putExtra("id", listaProductos[position].idProducto)
            startActivity(intent)
        }

        // Manejador de clics en el botón flotante (FloatingActionButton)
        floatingActionButton.setOnClickListener {
            // Creación de un intent para abrir la actividad NuevoProductoActivity
            val intent = Intent(this, NuevoProductoActivity::class.java)
            startActivity(intent)
        }
    }
}
