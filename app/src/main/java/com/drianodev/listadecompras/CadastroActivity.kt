package com.drianodev.listadecompras

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.app.Activity
import android.graphics.BitmapFactory
import androidx.activity.ComponentActivity
import kotlinx.android.synthetic.main.activity_cadastro.btn_inserir
import kotlinx.android.synthetic.main.activity_cadastro.img_foto_produto
import kotlinx.android.synthetic.main.activity_cadastro.txt_produto
import kotlinx.android.synthetic.main.activity_cadastro.txt_qtd
import kotlinx.android.synthetic.main.activity_cadastro.txt_valor

class CadastroActivity : ComponentActivity() {

    val COD_IMAGE = 101
    var imageBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cadastro)

        btn_inserir.setOnClickListener {

            val produto = txt_produto.text.toString()
            val	qtd = txt_qtd.text.toString()
            val	valor =	txt_valor.text.toString()

            if (produto.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()) {
                val prod = Produto(produto, qtd.toInt(), valor.toDouble(), imageBitmap)
                produtosGlobal.add(prod)
                txt_produto.text.clear()
                txt_qtd.text.clear()
                txt_valor.text.clear()
            } else {
                txt_produto.error =	if (txt_produto.text.isEmpty()) "Preencha o nome do	produto" else null
                txt_qtd.error = if (txt_qtd.text.isEmpty()) "Preencha a quantidade" else null
                txt_valor.error	= if (txt_valor.text.isEmpty())	"Preencha o valor" else null
            }
        }

        img_foto_produto.setOnClickListener {

            abrirGaleria()

        }
    }

    fun abrirGaleria() {

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), COD_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == COD_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val inputStream = contentResolver.openInputStream(data.getData()!!)
                imageBitmap = BitmapFactory.decodeStream(inputStream)
                img_foto_produto.setImageBitmap(imageBitmap)
            }
        }
    }
}