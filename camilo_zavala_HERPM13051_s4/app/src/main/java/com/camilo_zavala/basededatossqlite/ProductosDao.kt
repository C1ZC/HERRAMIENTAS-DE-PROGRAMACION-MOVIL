// Importación de paquetes necesarios
package com.camilo_zavala.basededatossqlite

import androidx.lifecycle.LiveData
import androidx.room.*

// Interfaz DAO (Data Access Object) para la entidad Producto
@Dao
interface ProductosDao {
    // Consulta para obtener todos los productos y retornarlos como LiveData
    @Query("SELECT * FROM productos")
    fun getAll(): LiveData<List<Producto>>
    // Consulta para obtener un producto por su ID y retornarlo como LiveData
    @Query("SELECT * FROM productos WHERE idProducto = :id")
    fun get(id: Int): LiveData<Producto>
    // Operación de inserción de uno o varios productos en la base de datos
    @Insert
    fun insertAll(vararg productos: Producto): List<Long>
    // Operación de actualización de un producto en la base de datos
    @Update
    fun update(producto: Producto)
    // Operación de eliminación de un producto de la base de datos
    @Delete
    fun delete(producto: Producto)
}