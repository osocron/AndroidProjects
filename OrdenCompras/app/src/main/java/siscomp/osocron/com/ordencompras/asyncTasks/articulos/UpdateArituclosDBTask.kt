package siscomp.osocron.com.ordencompras.asyncTasks.articulos

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import siscomp.osocron.com.ordencompras.asyncTasks.articulosDescr.UpdateArticulosDescrTask
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.entities.Articulo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosDescrRepo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosRepo


class UpdateArituclosDBTask(val ctx: Context,
                            val database: OrdenComprasDbHelper,
                            val articulos: List<Articulo>) : AsyncTask<Void, Void, Unit>() {

    val dialog = ProgressDialog(ctx)
    val articulosRepo = ArticulosRepo(database)

    override fun onPreExecute() {
        dialog.setMessage("Actualizando cambios...")
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun doInBackground(vararg p0: Void?) {
        articulos.forEach { a -> articulosRepo.insert(a) }
        return Unit
    }

    override fun onPostExecute(result: Unit?) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        val task = UpdateArticulosDescrTask(ctx, articulos, database)
        task.execute()
    }

}