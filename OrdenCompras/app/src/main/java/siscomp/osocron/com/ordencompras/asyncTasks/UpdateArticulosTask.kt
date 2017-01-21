package siscomp.osocron.com.ordencompras.asyncTasks

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import siscomp.osocron.com.ordencompras.model.json.Articulo
import siscomp.osocron.com.ordencompras.model.remote.ArticulosDescrRemoteRepo
import siscomp.osocron.com.ordencompras.model.remote.ArticulosRemoteRepo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosDescrRepo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosRepo


class UpdateArticulosTask(val ctx: Context,
                          val remoteRepo: ArticulosRemoteRepo,
                          val repo: ArticulosRepo,
                          val articulosDescrRemoteRepo: ArticulosDescrRemoteRepo,
                          val articulosDescrRepo: ArticulosDescrRepo) : AsyncTask<Unit, Unit, List<Articulo>?>(){

    val dialog = ProgressDialog(ctx)

    override fun onPreExecute() {
        dialog.setMessage("Obteniendo datos...")
        dialog.setCancelable(true)
        dialog.setOnCancelListener { cancel(false) }
        dialog.show()
    }

    override fun doInBackground(vararg p0: Unit?): List<Articulo>? {
        return remoteRepo.getAll()
    }

    override fun onPostExecute(result: List<Articulo>?) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        if (result != null) {
            val task = UpdateArituclosDBTask(ctx, repo, result, articulosDescrRemoteRepo, articulosDescrRepo)
            task.execute()
        }
        else Toast.makeText(ctx, "Error al obtener los datos", Toast.LENGTH_LONG).show()
    }

    override fun onCancelled() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        Toast.makeText(ctx, "Operacion cancelada", Toast.LENGTH_LONG).show()
    }

}