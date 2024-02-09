// Importación de paquetes necesarios
package com.camilo_zavala.basededatossqlite

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import java.io.File

// Objeto singleton para el control de imágenes
object ImageController {

    // Método para seleccionar una foto desde la galería
    // Parámetros:
    //   - activity: La actividad que invoca la selección
    //   - code: Código de solicitud para el resultado de la selección
    fun selectPhotoFromGallery(activity: Activity, code: Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        activity.startActivityForResult(intent, code)
    }

    // Método para guardar una imagen en el almacenamiento interno
    // Parámetros:
    //   - context: Contexto de la aplicación
    //   - id: Identificador asociado a la imagen
    //   - uri: URI de la imagen seleccionada
    fun saveImage(context: Context, id: Long, uri: Uri) {
        val file = File(context.filesDir, id.toString())

        val bytes = context.contentResolver.openInputStream(uri)?.readBytes()!!

        file.writeBytes(bytes)
    }

    // Método para obtener la URI de una imagen almacenada en el almacenamiento interno
    // Parámetros:
    //   - context: Contexto de la aplicación
    //   - id: Identificador asociado a la imagen
    // Retorna:
    //   - La URI de la imagen si existe, de lo contrario, una URI de marcador de posición
    fun getImageUri(context: Context, id: Long): Uri {
        val file = File(context.filesDir, id.toString())

        return if (file.exists()) Uri.fromFile(file)
        else Uri.parse("android.resource://com.camilo_zavala.basededatossqlite/drawable/placeholder")
    }

    // Método para eliminar una imagen del almacenamiento interno
    // Parámetros:
    //   - context: Contexto de la aplicación
    //   - id: Identificador asociado a la imagen a eliminar
    fun deleteImage(context: Context, id: Long) {
        val file = File(context.filesDir, id.toString())
        file.delete()
    }
}
