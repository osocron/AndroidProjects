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
                        val searchString: String) : AsyncTask<Unit, Unit, Unit>() {

    override fun doInBackground(vararg p0: Unit): Unit {
        data.clear()
        val clientes = repo.searchQuery(searchString)
        if (clientes != null) {
            data.addAll(clientes)
        }
    }

    override fun onPostExecute(result: Unit?) {
        ctx.runOnUiThread {
            adapter.notifyDataSetChanged()
        }
    }

}