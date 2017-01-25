package siscomp.osocron.com.ordencompras.asyncTasks

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import siscomp.osocron.com.ordencompras.model.entities.Articulo
import siscomp.osocron.com.ordencompras.model.json.ArticuloDescr
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosDescrRepo


class UpdateArticulosDescrTask(val ctx: Context,
                               val dataArticulos: List<Articulo>,
                               val articulosDescrRepo: ArticulosDescrRepo) : AsyncTask<Unit, Unit, List<ArticuloDescr>?>() {

    val dialog = ProgressDialog(ctx)

    override fun onPreExecute() {
        dialog.setMessage("Obteniendo datos...")
        dialog.setCancelable(true)
        dialog.setOnCancelListener { cancel(false) }
        dialog.show()
    }

    override fun doInBackground(vararg p0: Unit?): List<ArticuloDescr>? {
        return dataArticulos.map { ArticuloDescr(it.clave, it.descrgruma + ' ' + it.descripcio) }
    }

    override fun onPostExecute(result: List<ArticuloDescr>?) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        if (result != null) {
            val task = UpdateArticulosDescrDBTask(ctx, articulosDescrRepo, result)
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