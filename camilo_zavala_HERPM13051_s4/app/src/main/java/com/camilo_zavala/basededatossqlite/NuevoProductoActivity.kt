// Importación de paquetes necesarios
package com.camilo_zavala.basededatossqlite

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nuevo_producto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Actividad para agregar o editar un nuevo producto
class NuevoProductoActivity : AppCompatActivity() {

    // Código de solicitud para la selección de imágenes
    private val SELECT_ACTIVITY = 50
    private var imageUri: Uri? = null

    // Método llamado al crear la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_producto)

        // Inicialización de la variable que almacenará el ID del producto (si se está editando)
        var idProducto: Int? = null

        // Verificación si la actividad se inició con un producto existente (edición)
        if (intent.hasExtra("producto")) {
            // Obtención del producto desde los extras del intent
            val producto = intent.extras?.getSerializable("producto") as Producto

            // Configuración de los campos de la interfaz con los valores del producto existente
            nombre_et.setText(producto.nombre)
            precio_et.setText(producto.precio.toString())
            descripcion_et.setText(producto.descripcion)
            idProducto = producto.idProducto

            // Obtención de la URI de la imagen y configuración en el ImageView
            val imageUri = ImageController.getImageUri(this, idProducto.toLong())
            imageSelect_iv.setImageURI(imageUri)
        }

        // Obtención de la instancia de la base de datos Room
        val database = AppDatabase.getDatabase(this)

        // Manejador de clic en el botón de guardar
        save_btn.setOnClickListener {
            // Obtención de los valores de los campos de la interfaz
            val nombre = nombre_et.text.toString()
            val precio = precio_et.text.toString().toDouble()
            val descripcion = descripcion_et.text.toString()

            // Creación de un objeto Producto con los valores ingresados
            val producto = Producto(nombre, precio, descripcion, R.drawable.ic_launcher_background)

            // Verificación si se está editando un producto existente
            if (idProducto != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    // Asignación del ID al producto para actualizar la entrada correspondiente
                    producto.idProducto = idProducto

                    // Actualización del producto en la base de datos
                    database.productos().update(producto)

                    // Verificación de la existencia de una URI de imagen
                    imageUri?.let {
                        // Configuración de la URI en el intent de resultado
                        val intent = Intent()
                        intent.data = it
                        setResult(Activity.RESULT_OK, intent)

                        // Guardado de la imagen en el almacenamiento interno
                        ImageController.saveImage(this@NuevoProductoActivity, idProducto.toLong(), it)
                    }

                    // Finalización de la actividad
                    this@NuevoProductoActivity.finish()
                }
            } else { // Si no se está editando, se está creando un nuevo producto
                CoroutineScope(Dispatchers.IO).launch {
                    // Inserción del nuevo producto en la base de datos y obtención del ID asignado
                    val id = database.productos().insertAll(producto)[0]

                    // Verificación de la existencia de una URI de imagen
                    imageUri?.let {
                        // Guardado de la imagen en el almacenamiento interno
                        ImageController.saveImage(this@NuevoProductoActivity, id, it)
                    }

                    // Finalización de la actividad
                    this@NuevoProductoActivity.finish()
                }
            }
        }

        // Manejador de clic en el ImageView para seleccionar una imagen desde la galería
        imageSelect_iv.setOnClickListener {
            ImageController.selectPhotoFromGallery(this, SELECT_ACTIVITY)
        }
    }

    // Método llamado cuando se recibe un resultado de una actividad
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Verificación del código de solicitud y resultado de la actividad de selección de imágenes
        when {
            requestCode == SELECT_ACTIVITY && resultCode == Activity.RESULT_OK -> {
                // Obtención de la URI de la imagen seleccionada y configuración en el ImageView
                imageUri = data!!.data
                imageSelect_iv.setImageURI(imageUri)
            }
        }
    }
}
