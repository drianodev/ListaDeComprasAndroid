package com.drianodev.listadecompras

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.ComponentActivity
import	kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        btn_adicionar.setOnClickListener	{
            val	intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        val produtosAdapter = ProdutoAdapter(this)
        produtosAdapter.addAll(produtosGlobal)

        /*
        *   foi adicionado a o gradle(:app) o plugin: id 'kotlin-android-extensions'
        *   val list_view_produtos = findViewById<ListView>(R.id.list_view_produtos)
        */

        list_view_produtos.adapter = produtosAdapter

/*
        list_view_produtos.setOnItemClickListener { adapterView: AdapterView<*>, view: View, position: Int, id: Long ->

            val item = produtosAdapter.getItem(position)
            produtosAdapter.remove(item)
            true
        }
*/
        list_view_produtos.setOnItemClickListener { adapterView: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = produtosAdapter.getItem(position)

            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Deseja remover este item?")
                .setCancelable(false)
                .setPositiveButton("Sim") { dialog, _ ->
                    produtosAdapter.remove(item)
                    dialog.dismiss()
                }
                .setNegativeButton("Não") { dialog, _ ->
                    dialog.dismiss()
                }

            val alert = dialogBuilder.create()
            alert.show()
        }
    }

    override fun onResume() {
        super.onResume()
        val adapter = list_view_produtos.adapter as ProdutoAdapter
        adapter.clear()
        adapter.addAll(produtosGlobal)

        val soma = produtosGlobal.sumByDouble { it.valor * it.quantidade }
        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        txt_total.text = "TOTAL: ${ f.format(soma) }"
    }
}