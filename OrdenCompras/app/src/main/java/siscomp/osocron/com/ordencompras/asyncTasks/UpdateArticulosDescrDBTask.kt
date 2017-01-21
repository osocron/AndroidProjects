package siscomp.osocron.com.ordencompras.asyncTasks

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import siscomp.osocron.com.ordencompras.model.json.ArticuloDescr
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosDescrRepo


class UpdateArticulosDescrDBTask(ctx: Context,
                                 val articulosDescrRepo: ArticulosDescrRepo,
                                 val articulosDescr: List<ArticuloDescr>) : AsyncTask<Unit, Unit, Unit>() {

    val dialog = ProgressDialog(ctx)

    override fun onPreExecute() {
        dialog.setMessage("Actualizando cambios...")
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun doInBackground(vararg p0: Unit?) {
        articulosDescr.forEach { a -> articulosDescrRepo.insert(a) }
    }

    override fun onPostExecute(result: Unit?) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}