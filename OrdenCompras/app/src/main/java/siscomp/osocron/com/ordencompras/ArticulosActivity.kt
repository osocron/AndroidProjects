package siscomp.osocron.com.ordencompras

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import siscomp.osocron.com.ordencompras.adapters.ArticulosDescrBaseAdapter
import siscomp.osocron.com.ordencompras.asyncTasks.SearchArticuloDescrTask
import siscomp.osocron.com.ordencompras.asyncTasks.UpdateArticulosTask
import siscomp.osocron.com.ordencompras.model.db.database
import siscomp.osocron.com.ordencompras.model.json.ArticuloDescr
import siscomp.osocron.com.ordencompras.model.remote.ArticulosRemoteRepo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosDescrRepo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosRepo
import siscomp.osocron.com.ordencompras.views.CustomAutoCompleteTextView

class ArticulosActivity : AppCompatActivity() {

    val articulosRemoteRepo by lazy { ArticulosRemoteRepo(this) }
    val articulosRepo by lazy { ArticulosRepo(database) }
    val articulosDescrRepo by lazy { ArticulosDescrRepo(database) }

    val autoComplete by lazy { findViewById(R.id.articulosAutoComplete) as CustomAutoCompleteTextView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articulos)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpAutoComplete()
    }

    private fun setUpAutoComplete() {
        val data: MutableList<ArticuloDescr> = mutableListOf()
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menuActualizarArticuos -> {
                updateArticulos()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateArticulos() {
        val task = UpdateArticulosTask(this, articulosRemoteRepo, articulosRepo, articulosDescrRepo)
        task.execute()
    }

}
