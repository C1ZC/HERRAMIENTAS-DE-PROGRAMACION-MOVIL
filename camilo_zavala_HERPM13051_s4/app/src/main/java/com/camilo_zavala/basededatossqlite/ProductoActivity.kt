// Importación de paquetes necesarios
package com.camilo_zavala.basededatossqlite

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_producto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Actividad para mostrar detalles de un producto y permitir edición y eliminación
class ProductoActivity : AppCompatActivity() {

    // Variables de la actividad
    private lateinit var database: AppDatabase
    private lateinit var producto: Producto
    private lateinit var productoLiveData: LiveData<Producto>
    private val EDIT_ACTIVITY = 49

    // Método llamado al crear la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

        // Inicialización de la instancia de la base de datos Room
        database = AppDatabase.getDatabase(this)

        // Obtención del ID del producto desde los extras del intent
        val idProducto = intent.getIntExtra("id", 0)

        // Obtención de la URI de la imagen y configuración en el ImageView
        val imageUri = ImageController.getImageUri(this, idProducto.toLong())
        imagen.setImageURI(imageUri)

        // Obtención de un LiveData para el producto desde la base de datos
        productoLiveData = database.productos().get(idProducto)

        // Observador que actualiza la interfaz cuando hay cambios en el producto
        productoLiveData.observe(this, Observer {
            producto = it

            // Configuración de los datos del producto en la interfaz
            nombre_producto.text = producto.nombre
            precio_producto.text = "$${producto.precio}"
            detalles_producto.text = producto.descripcion
        })
    }

    // Método para crear el menú de opciones en la barra de acción
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.producto_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    // Método llamado al seleccionar un elemento del menú de opciones
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit_item -> {
                // Inicio de la actividad de edición del producto
                val intent = Intent(this, NuevoProductoActivity::class.java)
                intent.putExtra("producto", producto)
                startActivityForResult(intent, EDIT_ACTIVITY)
            }

            R.id.delete_item -> {
                // Detención de la observación del LiveData
                productoLiveData.removeObservers(this)

                // Eliminación del producto y su imagen asociada
                CoroutineScope(Dispatchers.IO).launch {
                    database.productos().delete(producto)
                    ImageController.deleteImage(this@ProductoActivity, producto.idProducto.toLong())
                    this@ProductoActivity.finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    // Método llamado al recibir un resultado de una actividad
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Verificación del código de solicitud y resultado de la actividad de edición
        when {
            requestCode == EDIT_ACTIVITY && resultCode == Activity.RESULT_OK -> {
                // Actualización de la imagen en el ImageView con la nueva imagen seleccionada
                imagen.setImageURI(data!!.data)
            }
        }
    }
}
