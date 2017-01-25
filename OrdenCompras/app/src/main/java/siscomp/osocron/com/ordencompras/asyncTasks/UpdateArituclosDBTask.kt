package siscomp.osocron.com.ordencompras.asyncTasks

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import siscomp.osocron.com.ordencompras.model.entities.Articulo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosDescrRepo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosRepo


class UpdateArituclosDBTask(val ctx: Context,
                            val articulosRepo: ArticulosRepo,
                            val articulos: List<Articulo>,
                            val articulosDescrRepo: ArticulosDescrRepo) : AsyncTask<Void, Void, Unit>() {

    val dialog = ProgressDialog(ctx)

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
        val task = UpdateArticulosDescrTask(ctx, articulos, articulosDescrRepo)
        task.execute()
    }

}