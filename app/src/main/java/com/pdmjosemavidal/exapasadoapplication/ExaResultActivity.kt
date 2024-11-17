package com.pdmjosemavidal.exapasadoapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pdmjosemavidal.exapasadoapplication.ExaMainActivity.Companion.MESA_KEY

class ExaResultActivity : AppCompatActivity() {
    private lateinit var viewMenRes: TextView

    private fun initComponents(){
        viewMenRes = findViewById(R.id.mensajeReserva)
    }

    private fun setMostrar(mensajeMost :String){
        viewMenRes.text = mensajeMost
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exa_result)

        val mensajeMostrar :String = (intent.extras?.getString(MESA_KEY) ?: -1.0).toString()

        initComponents()
        setMostrar(mensajeMostrar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}