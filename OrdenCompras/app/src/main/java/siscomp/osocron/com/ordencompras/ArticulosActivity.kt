package siscomp.osocron.com.ordencompras

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import siscomp.osocron.com.ordencompras.adapters.ArticulosDescrBaseAdapter
import siscomp.osocron.com.ordencompras.asyncTasks.articulos.UpdateArticulosTask
import siscomp.osocron.com.ordencompras.asyncTasks.articulosDescr.SearchArticuloDescrTask
import siscomp.osocron.com.ordencompras.asyncTasks.precios.OneTimerPreciosTask
import siscomp.osocron.com.ordencompras.model.db.database
import siscomp.osocron.com.ordencompras.model.json.JsonArticuloDescr
import siscomp.osocron.com.ordencompras.model.remote.PreciosRemoteRepo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosDescrRepo
import siscomp.osocron.com.ordencompras.model.repositories.PreciosRepo
import siscomp.osocron.com.ordencompras.views.CustomAutoCompleteTextView

class ArticulosActivity : AppCompatActivity() {

    val SCANNER_RESULT_CODE = 0

    val articulosDescrRepo by lazy { ArticulosDescrRepo(database) }

    val searchFAB by lazy { findViewById(R.id.fabSearchArticulos) as FloatingActionButton }
    val autoComplete by lazy { findViewById(R.id.articulosAutoComplete) as CustomAutoCompleteTextView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articulos)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpAutoComplete()
        searchFAB.setOnClickListener { searchArticulosButtonAction() }
        //OneTimerDetalladTask(this@ArticulosActivity, DetalladRemoteRepo(this@ArticulosActivity), DetalladRepo(database)).execute()
        //OneTimerExistencTask(this@ArticulosActivity, ExistencRemoteRepo(this@ArticulosActivity), ExistencRepo(database)).execute()
        //OneTimerPreciosTask(this@ArticulosActivity, PreciosRemoteRepo(this@ArticulosActivity), PreciosRepo(database)).execute()
    }

    private fun setUpAutoComplete() {
        val data: MutableList<JsonArticuloDescr> = mutableListOf()
        val adapter = ArticulosDescrBaseAdapter(data, this)
        autoComplete.setAdapter(adapter)
        autoComplete.threshold = 2
        autoComplete.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val task = SearchArticuloDescrTask(this@ArticulosActivity,
                        adapter, articulosDescrRepo, data, autoComplete.text.toString())
                task.execute()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.articulos_menu, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SCANNER_RESULT_CODE) {
            if (resultCode == RESULT_OK) {
                val result = data?.getStringExtra("SCAN_RESULT")
                if (result != null && result.isNotEmpty()) {
                    startSearch(result)
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this@ArticulosActivity, "Scan was Cancelled!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menuActualizarArticuos -> {
                updateArticulos()
                return true
            }
            R.id.mScanearArticulo -> {
                scanArticulo()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateArticulos() {
        val task = UpdateArticulosTask(this, database)
        task.execute()
    }

    private fun searchArticulosButtonAction() {
        if (validateInput()) {
            startSearch(autoComplete.text.toString())
        } else {
            Toast.makeText(this@ArticulosActivity, "Codigo no válido!", Toast.LENGTH_LONG).show()
        }
    }

    private fun startSearch(code: String) {
        //TODO implement search
        Toast.makeText(this@ArticulosActivity, code, Toast.LENGTH_LONG).show()
    }

    private fun scanArticulo() {
        try {
            val intent = Intent("com.google.zxing.client.android.SCAN")
            intent.putExtra("SCAN_MODE", "PRODUCT_MODE")
            intent.putExtra("SAVE_HISTORY", false)
            startActivityForResult(intent, SCANNER_RESULT_CODE)
        } catch (e: Exception) {
            Toast.makeText(this@ArticulosActivity, "Favor de instalar Barcode Scanner", Toast.LENGTH_LONG).show()
        }
    }

    private fun validateInput(): Boolean {
        return autoComplete.text.isNotEmpty() &&
                isNumeric(autoComplete.text) &&
                autoComplete.text.length >= 8
    }

    private fun isNumeric(text: CharSequence): Boolean {
        return text.all { Character.isDigit(it) }
    }



}
