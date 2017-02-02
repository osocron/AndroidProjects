package siscomp.osocron.com.ordencompras

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import siscomp.osocron.com.ordencompras.adapters.ClientesBaseAdapter
import siscomp.osocron.com.ordencompras.asyncTasks.clientes.SearchClienteTask
import siscomp.osocron.com.ordencompras.asyncTasks.clientes.UpdateClientTask
import siscomp.osocron.com.ordencompras.model.db.database
import siscomp.osocron.com.ordencompras.model.entities.Cliente
import siscomp.osocron.com.ordencompras.model.remote.ClienteRemoteRepo
import siscomp.osocron.com.ordencompras.model.repositories.ClientesRepo
import siscomp.osocron.com.ordencompras.views.CustomAutoCompleteTextView

class ClientesActivity : AppCompatActivity() {

    val clienteRemoteRepo by lazy { ClienteRemoteRepo(this) }
    val clienteRepo by lazy { ClientesRepo(database) }

    val autoComplete by lazy { findViewById(R.id.autoCompleteClientes) as CustomAutoCompleteTextView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clientes)
        setUpAutoComplete()
        //OneTimerClienteTask(this@ClientesActivity, ClienteRemoteRepo(this@ClientesActivity), ClientesRepo(database)).execute()
        //OneTimerArticulosTask(this@ClientesActivity, ArticulosRemoteRepo(this@ClientesActivity), ArticulosRepo(database)).execute()
        //OneTimerArticulosDescrTask(this@ClientesActivity, ArticulosDescrRepo(database)).execute()
        //OneTimerDetalladTask(this@ClientesActivity, DetalladRemoteRepo(this@ClientesActivity), DetalladRepo(database)).execute()
        //OneTimerExistencTask(this@ClientesActivity, ExistencRemoteRepo(this@ClientesActivity), ExistencRepo(database)).execute()
        //OneTimerPreciosTask(this@ClientesActivity, PreciosRemoteRepo(this@ClientesActivity), PreciosRepo(database)).execute()
    }

    private fun setUpAutoComplete() {
        val data: MutableList<Cliente> = mutableListOf()
        val adapter = ClientesBaseAdapter(data, this)
        autoComplete.setAdapter(adapter)
        autoComplete.threshold = 2
        autoComplete.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val task = SearchClienteTask(this@ClientesActivity,
                        adapter, clienteRepo, data, autoComplete.text.toString())
                task.execute()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.clientes_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.mUpdateClientes -> {
                updateClientes()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateClientes() {
        val task = UpdateClientTask(this, clienteRemoteRepo, clienteRepo)
        task.execute()
    }

}
