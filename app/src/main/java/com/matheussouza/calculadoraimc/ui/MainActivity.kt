package com.matheussouza.calculadoraimc.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.matheussouza.calculadoraimc.R
import com.matheussouza.calculadoraimc.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.matheussouza.imccalculator.viewmodel.ImcViewModel


class MainActivity : AppCompatActivity(){
    private val viewModel: ImcViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       val altura_IMC = findViewById<EditText>(R.id.altura_IMC)
        val peso_IMC = findViewById<EditText>(R.id.peso_IMC)
         val btn_calcular = findViewById<Button>(R.id.btn_calcular)
         val btn_limpar = findViewById<Button>(R.id.btn_limpar)
         val text_resultado = findViewById<TextView>(R.id.resultado)


        btn_limpar.setOnClickListener {
            peso_IMC.setText("")
            altura_IMC.setText("")
        }
        btn_calcular.setOnClickListener{
            val peso= peso_IMC.text.toString().replace(",", ".").toFloatOrNull()
            val altura= altura_IMC.text.toString().replace(",", ".").toFloatOrNull()

            if (peso == null || altura == null || peso <= 0 || altura <=0){
                Toast.makeText(this, "Preencha os campos corretamente!", Toast.LENGTH_SHORT).show()
            }else{
               viewModel.calcularImc(peso, altura)
            }
        }

        viewModel.resultado.observe(this, Observer { resultado ->
            val texto = "Seu IMC é: ${resultado.imc}\nClassificação: ${resultado.classificacao}"
            text_resultado.text = texto
        })
    }
}

