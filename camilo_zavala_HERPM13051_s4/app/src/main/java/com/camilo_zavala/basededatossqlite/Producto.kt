// Importación de paquetes necesarios
package com.camilo_zavala.basededatossqlite

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// Definición de la entidad Producto con la anotación @Entity
@Entity(tableName = "productos")
class Producto(
    // Nombre del producto
    val nombre:String,
    // Precio del producto
    val precio: Double,
    // Descripción del producto
    val descripcion: String,
    // Recurso de imagen asociado al producto
    val imagen: Int,
    @PrimaryKey(autoGenerate = true)
    // Identificador único autogenerado para el producto
    var idProducto: Int = 0
) : Serializable