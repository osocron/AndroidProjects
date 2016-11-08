package fei.mx.uv.blog_de_notas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    val agregarButton by lazy { findViewById(R.id.agregarNotaButton) as Button }
    val listaNotas by lazy { findViewById(R.id.notasListView) as ListView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        agregarButton.setOnClickListener { agregarNota() }
    }

    override fun onStart() {
        super.onStart()
        mostrarLista(cargarNotas())
    }

    private fun cargarNotas(): List<String> {
        return getSharedPreferences("NOTAS_DAR",
                Context.MODE_PRIVATE).all.keys.toList()
    }

    private fun mostrarLista(notas: List<String>) {
        val adapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                notas)
        listaNotas.adapter = adapter
        listaNotas.setOnItemClickListener { adapterView, view, i, l ->
            editarNota(listaNotas.getItemAtPosition(i).toString())
        }
        listaNotas.setOnItemLongClickListener { adapterView, view, i, l ->
            preguntarEliminar(listaNotas.getItemAtPosition(i).toString())
            true
        }
    }

    private fun editarNota(nombreNota: String) {
        val intent = Intent(this, EditarNotaActivity::class.java)
        intent.putExtra("val_modo", "E")
        intent.putExtra("val_nombre_nota", nombreNota)
        startActivity(intent)
    }

    private fun preguntarEliminar(nombreNota: String) {
        val dialogo = AlertDialog.Builder(this).create()
        dialogo.setTitle("Eliminar nota")
        dialogo.setMessage("¿Desea eliminar la nota: $nombreNota?")
        dialogo.setButton(AlertDialog.BUTTON_POSITIVE, "SI",
                { dialog, which -> eliminarNota(nombreNota) })
        dialogo.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                { dialog, which -> dialog.dismiss() })
    }

    private fun eliminarNota(nombreNota: String) {
        val sp = getSharedPreferences("NOTAS_DAR", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.remove(nombreNota)
        editor.apply()
        mostrarLista(cargarNotas())
    }

    private fun agregarNota() {
        val intent = Intent(this, EditarNotaActivity::class.java)
        intent.putExtra("val_modo", "N")
        startActivity(intent)
    }

}
