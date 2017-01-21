package siscomp.osocron.com.ordencompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AutoCompleteTextView
import siscomp.osocron.com.ordencompras.asyncTasks.UpdateArticulosTask
import siscomp.osocron.com.ordencompras.model.db.database
import siscomp.osocron.com.ordencompras.model.remote.ArticulosDescrRemoteRepo
import siscomp.osocron.com.ordencompras.model.remote.ArticulosRemoteRepo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosDescrRepo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosRepo

class ArticulosActivity : AppCompatActivity() {

    val articulosRemoteRepo by lazy { ArticulosRemoteRepo(this) }
    val articulosRepo by lazy { ArticulosRepo(database) }
    val articulosDescrRemoteRepo by lazy { ArticulosDescrRemoteRepo(this) }
    val articulosDescrRepo by lazy { ArticulosDescrRepo(database) }

    val autoComplete by lazy { findViewById(R.id.articulosAutoComplete) as AutoCompleteTextView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articulos)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
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
        val task = UpdateArticulosTask(this, articulosRemoteRepo, articulosRepo, articulosDescrRemoteRepo, articulosDescrRepo)
        task.execute()
    }

}
