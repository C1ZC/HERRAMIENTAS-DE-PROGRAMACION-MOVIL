package com.cizc.camilo_zavala_herpm13051_s5.adapter

/**
 * Clase Vendedor: Representa un vendedor en la aplicación.
 * @param nombre El nombre del vendedor.
 * @param foto URL o referencia a la imagen del vendedor.
 * @param area Área de especialización o departamento del vendedor.
 */
data class Vendedor(
    val nombre: String,
    val foto: String, // Puede ser una URL o un recurso drawable
    val area: String
)
