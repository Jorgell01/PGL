package com.example.listadoperso

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var productList: ArrayList<Product>
    private lateinit var adapter: CustomAdapter
    private lateinit var totalInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productList = ArrayList()
        adapter = CustomAdapter(this, productList)

        val listView: ListView = findViewById(R.id.lista_productos)
        listView.adapter = adapter

        totalInfo = findViewById(R.id.total_compra)

        val addButton: Button = findViewById(R.id.aniadir_button)
        addButton.setOnClickListener {
            addProduct()
        }

        registerForContextMenu(listView) // Para el menú contextual
    }

    private fun addProduct() {
        val nameInput: EditText = findViewById(R.id.nombre_producto)
        val quantityInput: EditText = findViewById(R.id.cantidad_producto)
        val priceInput: EditText = findViewById(R.id.precio_producto)

        val name = nameInput.text.toString()
        val quantity = if (quantityInput.text.isNotEmpty()) quantityInput.text.toString().toInt() else 0
        val price = if (priceInput.text.isNotEmpty()) priceInput.text.toString().toDouble() else 0.0

        val product = Product(name, quantity, price)
        productList.add(product)
        adapter.notifyDataSetChanged()
        updateTotal()
    }

    private fun updateTotal() {
        val totalItems = productList.size
        val totalPrice = productList.sumByDouble { it.price }
        totalInfo.text = "Lista de la compra: $totalItems productos = $totalPrice €"
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_contextual, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.delete -> {
                productList.removeAt(info.position)
                adapter.notifyDataSetChanged()
                updateTotal()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}
