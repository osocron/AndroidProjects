package siscomp.osocron.com.ordencompras.asyncTasks

import android.content.Context
import android.os.AsyncTask
import org.jetbrains.anko.runOnUiThread
import siscomp.osocron.com.ordencompras.adapters.ClientesBaseAdapter
import siscomp.osocron.com.ordencompras.model.entities.Cliente
import siscomp.osocron.com.ordencompras.model.repositories.ClientesRepo

class SearchClienteTask(val ctx: Context,
                        val adapter: ClientesBaseAdapter,
                        val repo: ClientesRepo,
                        val data: MutableList<Cliente>,
                        val searchString: String) : AsyncTask<Unit, Unit, Boolean>()     {

    override fun doInBackground(vararg p0: Unit): Boolean {
        val clientes = repo.searchQuery(searchString)
        if (clientes != null) {
            ctx.runOnUiThread {
                data.clear()
                data.addAll(clientes)
            }
            return true
        } else return false
    }

    override fun onPostExecute(result: Boolean) {
        if (result) {
            ctx.runOnUiThread {
                adapter.notifyDataSetChanged()
            }
        }
    }

}