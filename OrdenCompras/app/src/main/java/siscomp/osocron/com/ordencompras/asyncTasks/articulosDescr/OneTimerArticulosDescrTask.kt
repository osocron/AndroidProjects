package siscomp.osocron.com.ordencompras.asyncTasks.articulosDescr

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import siscomp.osocron.com.ordencompras.model.json.JsonArticuloDescr
import siscomp.osocron.com.ordencompras.model.remote.ArticulosRemoteRepo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosDescrRepo


class OneTimerArticulosDescrTask(val ctx: Context,
                                 val articulosDescrRepo: ArticulosDescrRepo) : AsyncTask<Unit, Unit, Unit>() {

    val dialog = ProgressDialog(ctx)

    override fun onPreExecute() {
        dialog.setMessage("Actualizando cambios...")
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun doInBackground(vararg p0: Unit?) {
        val data = ArticulosRemoteRepo(ctx).getAll()?.map { JsonArticuloDescr(it.clave, it.descrgruma + ' ' + it.descripcio) }
        data?.forEach { a -> articulosDescrRepo.insert(a) }
    }

    override fun onPostExecute(result: Unit?) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        Toast.makeText(ctx, "Proceso finalizado", Toast.LENGTH_LONG).show()
    }
}