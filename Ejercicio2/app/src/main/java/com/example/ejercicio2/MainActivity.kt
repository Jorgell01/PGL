package com.example.ejercicio2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.Person
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//Jorge A. Herrero Santana

class MainActivity : AppCompatActivity() {

    object datosPropietario {
        const val NAME:String = "Jorge"
    }

    object datosUsuario {
        const val NAME:String = "Jorge"
        const val AGE:Int = 23
        val HOBBIES:List<String> = listOf("Lol", "Cagar", "Domir")
    }

    object etLog {
        const val TAG:String = ":!:!:!TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val person : Persona = Persona(datosUsuario.NAME, datosUsuario.AGE, datosUsuario.HOBBIES)
        botDeSeguridad(person)
    }

    private fun botDeSeguridad(person: Persona) {
        if (person.name == datosPropietario.NAME) {
            when(person.age) {
                in 0 .. 17 -> Log.d(etLog.TAG, getString(R.string.mensaje_error_menor))
                in 65 .. 100 -> Log.d(etLog.TAG, getString(R.string.mensaje_error_viejo))
                in 18 .. 65 -> Log.d(etLog.TAG, getString(R.string.mensaje_okey_autorizado) + person.hobbies)
                else -> Log.d(etLog.TAG, getString(R.string.mensaje_else))
            }
        } else {
            Log.d(etLog.TAG, getString(R.string.mensaje_error_acceso))
        }
    }
}

data class Persona(
    val name:String,
    val age:Int,
    val hobbies: List<String>
)