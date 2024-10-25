package com.example.listadoperso

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomAdapter(context: Context, private val productList: ArrayList<Product>) :
    ArrayAdapter<Product>(context, 0, productList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.productos_distro, parent, false)
        }

        val product = getItem(position)

        val productName = view!!.findViewById<TextView>(R.id.nombre_producto)
        val productQuantity = view.findViewById<TextView>(R.id.cantidad_producto)
        val productPrice = view.findViewById<TextView>(R.id.precio_producto)

        productName.text = product?.name
        productQuantity.text = product?.quantity.toString()
        productPrice.text = product?.price.toString()

        return view
    }
}
