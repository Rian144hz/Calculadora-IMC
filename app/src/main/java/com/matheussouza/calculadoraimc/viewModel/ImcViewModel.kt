package com.matheussouza.imccalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matheussouza.calculadoraimc.model.ImcResult

class ImcViewModel : ViewModel() {

    private val _resultado = MutableLiveData<ImcResult>()
    val resultado: LiveData<ImcResult> = _resultado

    fun calcularImc(peso: Float, altura: Float) {
        if (peso <= 0f || altura <= 0f) return

        val imc = peso / (altura * altura)
        val classificacao = when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25 -> "Peso normal"
            imc < 30 -> "Sobrepeso"
            imc < 35 -> "Obesidade grau 1"
            imc < 40 -> "Obesidade grau 2"
            else -> "Obesidade grau 3"
        }

        _resultado.value = ImcResult(imc, classificacao)
    }
}
