package fei.mx.uv.formularioregistro

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    /**
     *  Programar sin modificar variables no imposible ;)
     * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (findViewById(R.id.registroButton) as Button).setOnClickListener {
            onClickAction()
        }
    }

    fun validateTextFields(editText: EditText): Boolean {
        val b = editText.text.isEmpty()
        if (b) editText.error = "El campo no puede estar vacio!"
        return b
    }

    fun onClickAction(): Unit {
        val editTexts = listOf<View>(
                findViewById(R.id.nombresInput),
                findViewById(R.id.apellidosInput),
                findViewById(R.id.emailInput),
                findViewById(R.id.telefonoInput))
        val validated = editTexts.map { v -> validateTextFields(v as EditText) }
        if (validated.all{ b -> !b })
            createDialog(editTexts +
                    findViewById(R.id.ciudadesSpinner) +
                    findViewById(R.id.comidasSpinner))
    }

    fun createDialog(views: List<View>): Unit {
        val dialogo = AlertDialog.Builder(this@MainActivity).create()
        dialogo.setTitle("Registro")
        val valores = viewToText(views)
        val message = "Los valores ingresados son:\n" +
                "\nNombres: " + valores[0] +
                "\nApellidos: " + valores[1] +
                "\nEmail: " + valores[2] +
                "\nTelefono: " + valores[3] +
                "\nCiudad: " + valores[4] +
                "\nComida: " + valores[5] +
                "\n\nEsta seguro de querer realizar el registro?"
        dialogo.setMessage(message)
        dialogo.setButton(AlertDialog.BUTTON_POSITIVE, "Acepatar") { dialog, which -> startNewActivity(valores) }
        dialogo.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar") { dialog, which -> dialog.dismiss() }
        dialogo.show()
    }

    fun viewToText(views: List<View>): List<String> {
        return views.map {
            when (it) {
                is EditText -> it.text
                is Spinner  -> it.selectedItem.toString()
                else -> "" }
        }.map { it.toString() }
    }

    fun startNewActivity(valores: List<String>): Unit {
        val intent = Intent(this, SegundaActivity::class.java)
        intent.putExtra("val_nombres", valores[0])
        intent.putExtra("val_apellidos", valores[1])
        intent.putExtra("val_email", valores[2])
        intent.putExtra("val_telefono", valores[3])
        intent.putExtra("val_ciudad", valores[4])
        intent.putExtra("val_comida", valores[5])
        startActivity(intent)
    }

}
