package com.pdmjosemavidal.exapasadoapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExaMainActivity : AppCompatActivity() {
    private lateinit var viewDos: CardView
    private lateinit var viewCuatro: CardView
    private lateinit var viewOcho: CardView
    private lateinit var viewHoras: TextView
    private lateinit var btnPatras: FloatingActionButton
    private lateinit var btnPalante: FloatingActionButton
    private lateinit var btnCheck: ImageButton
    private lateinit var btnName: EditText

    private var dos = true
    private var cuatro = false
    private var ocho = false
    private val horas = listOf("20:00", "21:00", "22:00")
    private var currentIndex = 0
    private var name = "Nombre"
    private var numPersonas = "2"
    private var hora = "20:00"

    companion object{
        const val MESA_KEY = "HOLA"
    }


    private fun initComponents(){
        viewDos=findViewById(R.id.medos)
        viewCuatro=findViewById(R.id.mecuatro)
        viewOcho=findViewById(R.id.meocho)
        viewHoras=findViewById(R.id.horas)
        btnPatras = findViewById(R.id.patras)
        btnPalante = findViewById(R.id.palante)
        btnCheck = findViewById(R.id.check)
        btnName = findViewById(R.id.nombre)

    }
    private fun initListeners(){
        viewDos.setOnClickListener{
            setDos()
        }
        viewCuatro.setOnClickListener{
            setCuatro()
        }
        viewOcho.setOnClickListener{
            setOcho()
        }
        btnPatras.setOnClickListener{
            currentIndex = if (currentIndex - 1 < 0) horas.size - 1 else currentIndex - 1
            updateViewHoras()
        }
        btnPalante.setOnClickListener{
            currentIndex = (currentIndex + 1) % horas.size
            updateViewHoras()
        }
        btnCheck.setOnClickListener{
            setName()
            setHora()
            comprobarName()
        }
    }

    private fun comprobarName(){
        if(btnName.text.toString().isEmpty()){
            Toast.makeText(this, "La reserva necesita un nombre", Toast.LENGTH_LONG).show()
        }else{
            val intentGA = Intent(this, ExaResultActivity::class.java)
            intentGA.putExtra(MESA_KEY, navigate2Result())
            startActivity(intentGA)
        }
    }

    private fun navigate2Result(): String{
        val resultado = "MESA RESERVADA PARA $numPersonas PERSONAS A NOMBRE DE $name A LAS $hora"
        return resultado
    }

    private fun updateViewHoras(){
        viewHoras.text = horas[currentIndex]
    }

    private fun initUI(){
        setDos()
    }
    private fun setName(){
        name = btnName.text.toString()
    }
    private fun setHora(){
        hora = viewHoras.text.toString()
    }
    private fun setDos(){
        dos = true
        cuatro= false
        ocho = false
        setGenderColor()
    }
    private fun setCuatro(){
        dos = false
        cuatro= true
        ocho= false
        setGenderColor()
    }
    private fun setOcho(){
        dos = false
        cuatro= false
        ocho= true
        setGenderColor()
    }
    private fun setGenderColor(){
      if(dos==true){
         viewDos.setCardBackgroundColor(getColor(R.color.elemsel))
          viewCuatro.setCardBackgroundColor(getColor(R.color.fondo))
          viewOcho.setCardBackgroundColor(getColor(R.color.fondo))
          numPersonas = "2"
      }else if(cuatro==true){
          viewCuatro.setCardBackgroundColor(getColor(R.color.elemsel))
          viewDos.setCardBackgroundColor(getColor(R.color.fondo))
          viewOcho.setCardBackgroundColor(getColor(R.color.fondo))
          numPersonas = "4"
      }else{
          viewOcho.setCardBackgroundColor(getColor(R.color.elemsel))
          viewCuatro.setCardBackgroundColor(getColor(R.color.fondo))
          viewDos.setCardBackgroundColor(getColor(R.color.fondo))
          numPersonas = "8"
      }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exa_main)
        initComponents()
        initListeners()
        initUI()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}