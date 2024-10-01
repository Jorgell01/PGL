package com.example.proyecto2activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Encuentra el botón y el campo de texto por su ID
        val editTextName: EditText = findViewById(R.id.editTextName)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            // Recoge el nombre del usuario
            val name = editTextName.text.toString()

            // Crea un intent para pasar a la SecondActivity
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("USER_NAME", name)  // Añade el nombre al intent
            startActivity(intent)
        }
    }
}