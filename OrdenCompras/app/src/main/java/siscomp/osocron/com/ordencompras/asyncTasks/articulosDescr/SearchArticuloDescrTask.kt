package siscomp.osocron.com.ordencompras.asyncTasks.articulosDescr

import android.content.Context
import android.os.AsyncTask
import org.jetbrains.anko.runOnUiThread
import siscomp.osocron.com.ordencompras.adapters.ArticulosDescrBaseAdapter
import siscomp.osocron.com.ordencompras.model.json.JsonArticuloDescr
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosDescrRepo


class SearchArticuloDescrTask(val ctx: Context,
                              val adapter: ArticulosDescrBaseAdapter,
                              val repo: ArticulosDescrRepo,
                              val data: MutableList<JsonArticuloDescr>,
                              val searchString: String) : AsyncTask<Unit, Unit, Boolean>(){

    override fun doInBackground(vararg p0: Unit?): Boolean {
        val articulos = repo.searchQuery(searchString)
        if (articulos != null) {
            ctx.runOnUiThread {
                data.clear()
                data.addAll(articulos)
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