// Importación de paquetes necesarios
package com.camilo_zavala.basededatossqlite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_producto.view.*

// Adaptador personalizado para la lista de productos
class ProductosAdapter(private val mContext: Context, private val listaProductos: List<Producto>) : ArrayAdapter<Producto>(mContext, 0, listaProductos) {

    // Método para obtener la vista de un elemento en la lista
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflación del diseño de elemento de producto
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_producto, parent, false)

        // Obtención del producto en la posición actual
        val producto = listaProductos[position]

        // Configuración de los datos del producto en los elementos de la vista
        layout.nombre.text = producto.nombre
        layout.precio.text = "$${producto.precio}"

        // Obtención de la URI de la imagen y configuración en el ImageView
        val imageUri = ImageController.getImageUri(mContext, producto.idProducto.toLong())
        layout.imageView.setImageURI(imageUri)

        // Retorno de la vista configurada
        return layout
    }
}
