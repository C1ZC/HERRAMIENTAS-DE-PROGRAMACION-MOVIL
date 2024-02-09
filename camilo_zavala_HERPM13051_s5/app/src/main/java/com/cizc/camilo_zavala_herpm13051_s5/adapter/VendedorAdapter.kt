package com.cizc.camilo_zavala_herpm13051_s5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cizc.camilo_zavala_herpm13051_s5.R

class VendedorAdapter(
    private val listaVendedores: List<Vendedor>,
    private val onClickListener: (Int) -> Unit
) : RecyclerView.Adapter<VendedorAdapter.VendedorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VendedorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vendedor, parent, false)
        return VendedorViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: VendedorViewHolder, position: Int) {
        val vendedor = listaVendedores[position]
        holder.bind(vendedor)
    }

    override fun getItemCount() = listaVendedores.size

    class VendedorViewHolder(itemView: View, private val onClickListener: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.textViewNombreVendedor)
        private val areaTextView: TextView = itemView.findViewById(R.id.textViewAreaVendedor)
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewVendedor)

        init {
            itemView.setOnClickListener {
                onClickListener(adapterPosition)
            }
        }

        fun bind(vendedor: Vendedor) {
            nombreTextView.text = vendedor.nombre
            areaTextView.text = vendedor.area
            // Aquí deberías cargar la imagen del vendedor, por ejemplo usando Glide o Picasso
        }
    }
}
