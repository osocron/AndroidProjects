package fei.mx.uv.blog_de_notas

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class EditarNotaActivity : AppCompatActivity() {

    val cancelarButton by lazy { findViewById(R.id.buttonCancelar) as Button }
    val guardarButton by lazy { findViewById(R.id.buttonGuardar) as Button }
    val editTextNombre by lazy { findViewById(R.id.editTextNombre) as EditText }
    val editTextNota by lazy { findViewById(R.id.editTextContenido) as EditText }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_nota)
        val modo = leerParametrosIntent()
        cancelarButton.setOnClickListener { cancelar() }
        guardarButton.setOnClickListener { guardar(modo) }
    }

    private fun leerParametrosIntent(): String {
        val intent = getIntent()
        val modo = intent.getStringExtra("val_modo")
        if (modo != null && modo == "E") {
            val nombre = intent.getStringExtra("val_nombre_nota")
            editTextNombre.setText(nombre)
            editTextNombre.isEnabled = false
            val sp = getSharedPreferences("NOTAS_DAR", Context.MODE_PRIVATE)
            editTextNota.setText(sp.getString(nombre, ""))
            return nombre
        } else {
            return "N"
        }
    }

    private fun guardar(modo: String) {
        if (!validarDatosNombre() &&
                !validarDatosContenido() &&
                !validarDatosDuplicados(modo)) {
            val nombre = editTextNombre.text.toString()
            val nota = editTextNota.text.toString()
            val sp = getSharedPreferences("NOTAS_DAR", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString(nombre, nota)
            editor.apply()
            finish()
        }
    }

    private fun validarDatosNombre(): Boolean {
        if (editTextNombre.text == null || editTextNombre.text.isEmpty()) {
            editTextNombre.error = "Debes introducir el nombre de la nota"
            return true
        } else { return false }
    }

    private fun validarDatosContenido(): Boolean {
        if (editTextNota.text == null || editTextNota.text.isEmpty()) {
            editTextNota.error = "Debes introducir el contenido de la nota"
            return true
        } else { return false }
    }

    private fun validarDatosDuplicados(modo: String): Boolean {
        if (editTextNombre.text != null) {
            val nombre = editTextNombre.text.toString()
            val sp = getSharedPreferences("NOTAS_DAR", Context.MODE_PRIVATE)
            if (modo == "N" && sp != null && sp.contains(nombre)) {
                editTextNombre.error = "El nombre de la nota ya se encuentra ocupado, " +
                        "introduce otro"
                return true
            } else {
                return false
            }
        } else {
            return false
        }
    }

    private fun cancelar() { finish() }

}
