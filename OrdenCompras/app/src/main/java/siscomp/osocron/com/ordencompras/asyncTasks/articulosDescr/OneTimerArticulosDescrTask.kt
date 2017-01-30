package siscomp.osocron.com.ordencompras.asyncTasks.articulosDescr

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import siscomp.osocron.com.ordencompras.model.json.JsonArticuloDescr
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosDescrRepo


class OneTimerArticulosDescrTask(val ctx: Context,
                                 val articulosDescrRepo: ArticulosDescrRepo,
                                 val articulosDescrJson: List<JsonArticuloDescr>) : AsyncTask<Unit, Unit, Unit>() {

    val dialog = ProgressDialog(ctx)

    override fun onPreExecute() {
        dialog.setMessage("Actualizando cambios...")
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun doInBackground(vararg p0: Unit?) {
        articulosDescrJson.forEach { a -> articulosDescrRepo.insert(a) }
    }

    override fun onPostExecute(result: Unit?) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        Toast.makeText(ctx, "Proceso finalizado", Toast.LENGTH_LONG).show()
    }
}