// Importación de paquetes necesarios
package com.camilo_zavala.basededatossqlite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Definición de la base de datos con la anotación @Database
@Database(entities = [Producto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // Declaración de un método abstracto para acceder a la tabla de productos
    abstract fun productos(): ProductosDao

    // Objeto companion para implementar el patrón Singleton y obtener una instancia de la base de datos
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Método para obtener una instancia de la base de datos
        fun getDatabase(context: Context): AppDatabase {
            // Verificación de existencia de una instancia previa
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            // Sincronización para evitar problemas de concurrencia al crear una nueva instancia
            synchronized(this) {
                // Creación de la instancia de la base de datos utilizando Room.databaseBuilder
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()

                // Almacenar la instancia creada para reutilización
                INSTANCE = instance

                // Devolver la instancia de la base de datos
                return instance
            }
        }
    }
}
