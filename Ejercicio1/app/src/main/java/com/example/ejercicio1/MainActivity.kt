package com.example.ejercicio1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//Jorge A. Herrero Santana

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
        val programador = Programador()
        val datos = programador.getProgrammerData()
    }

    class Programador: ProgramadorInterface {

        private fun getName(): String {
            return "Jorge Herrero Santana"
        }

        private fun getAge(): Int {
            return 23
        }

        private fun getLanguage(): String {
            return "Kotlin"
        }

        override fun getProgrammerData(): ProgrammerData {
            return ProgrammerData(getName(), getAge(), getLanguage())
        }
    }
}

data class ProgrammerData(
    val name: String,
    val age: Int,
    val language: String
)