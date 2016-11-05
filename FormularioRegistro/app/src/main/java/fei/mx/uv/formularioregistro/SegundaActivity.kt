package fei.mx.uv.formularioregistro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SegundaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)
        val nombres   = intent.getStringExtra("val_nombres")
        val apellidos = intent.getStringExtra("val_apellidos")
        val email     = intent.getStringExtra("val_email")
        val telefono  = intent.getStringExtra("val_telefono")
        val ciudad    = intent.getStringExtra("val_ciudad")
        val comida    = intent.getStringExtra("val_comida")
        val tViewNombre   = findViewById(R.id.nombresTV) as TextView
        val tViewApellid  = findViewById(R.id.apellidosTV) as TextView
        val tViewEmail    = findViewById(R.id.emailTV) as TextView
        val tViewTelefono = findViewById(R.id.telefonoTV) as TextView
        val tViewCiudad   = findViewById(R.id.ciudadTV) as TextView
        val tViewComida   = findViewById(R.id.comidaTV) as TextView
        tViewNombre.text = nombres
        tViewApellid.text = apellidos
        tViewEmail.text = email
        tViewTelefono.text = telefono
        tViewCiudad.text = ciudad
        tViewComida.text = comida
    }
}
