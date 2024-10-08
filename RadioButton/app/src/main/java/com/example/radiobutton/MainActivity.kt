package com.example.radiobutton

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.radiobutton.R

class MainActivity : AppCompatActivity() {

    private lateinit var mensaje: TextView
    private lateinit var rgSemana: RadioGroup
    private lateinit var listado: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar los controles
        initControles()
        // Inicializar el listado (Spinner)
        initListado()
        // Configurar el mensaje mostrado
        mostrarMensaje()
    }

    // Inicializar los controles (TextView y RadioGroup)
    private fun initControles() {
        mensaje = findViewById(R.id.tvMensajeBienvenida)
        rgSemana = findViewById(R.id.rgSemana)
    }

    // Inicializar el Spinner con las opciones "Mañana", "Tarde" y "Noche"
    private fun initListado() {
        listado = findViewById(R.id.spinner)
        val datosListado: Array<String> = arrayOf("Mañana", "Tarde", "Noche")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, datosListado)
        listado.adapter = adapter
        listado.setSelection(0) // Seleccionar "Mañana" por defecto
    }

    // Configurar el mensaje basado en la selección del RadioGroup y Spinner
    private fun mostrarMensaje() {
        rgSemana.setOnCheckedChangeListener { group, checkedId ->
            // Obtener el RadioButton seleccionado
            val rbSeleccionado: RadioButton = findViewById(checkedId)
            // Actualizar el TextView con el saludo basado en el día seleccionado y el tiempo
            mensaje.text = getString(R.string.mensajeSaludo) + " " + rbSeleccionado.text + "\n POR LA " + listado.selectedItem
        }
    }
}
