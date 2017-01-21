package siscomp.osocron.com.ordencompras

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton

class MainActivity : AppCompatActivity() {

    val crearButton by lazy { findViewById(R.id.fabCrear) as FloatingActionButton }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        crearButton.setOnClickListener {
            irActivityCrearPedido()
        }
    }

    private fun irActivityCrearPedido() {
        val intent = Intent(this@MainActivity, ArticulosActivity::class.java)
        startActivity(intent)
    }

}
