package siscomp.osocron.com.ordencompras.asyncTasks.articulosDescr

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import siscomp.osocron.com.ordencompras.asyncTasks.detallad.UpdateDetalladTask
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.entities.Articulo
import siscomp.osocron.com.ordencompras.model.json.JsonArticuloDescr
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosDescrRepo


class UpdateArticulosDescrTask(val ctx: Context,
                               val dataArticulos: List<Articulo>,
                               val database: OrdenComprasDbHelper) : AsyncTask<Unit, Unit, Unit?>() {

    val dialog = ProgressDialog(ctx)
    val articulosDescrRepo = ArticulosDescrRepo(database)

    override fun onPreExecute() {
        dialog.setMessage("Procesando datos...")
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun doInBackground(vararg p0: Unit?) {
        dataArticulos.map {
            JsonArticuloDescr(it.clave, it.descrgruma + ' ' + it.descripcio)
        }.forEach { articulosDescrRepo.insert(it) }
    }

    override fun onPostExecute(result: Unit?) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        val task = UpdateDetalladTask(ctx, dataArticulos, database)
        task.execute()
    }

}