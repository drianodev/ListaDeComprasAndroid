package com.drianodev.listadecompras

import android.graphics.Bitmap

data class Produto (val nome: String, val quantidade: Int, val valor: Double, val imagem: Bitmap? = null) {

}