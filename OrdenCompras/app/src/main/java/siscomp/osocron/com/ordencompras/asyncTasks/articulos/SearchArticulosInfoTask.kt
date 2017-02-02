package siscomp.osocron.com.ordencompras.asyncTasks.articulos

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.view.LayoutInflater
import siscomp.osocron.com.ordencompras.R
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper


class SearchArticulosInfoTask(val ctx: Context,
                              val database: OrdenComprasDbHelper,
                              val clave: String?) : AsyncTask<Unit, Unit, Unit>() {

    val dialog = ProgressDialog(ctx)

    override fun onPreExecute() {
        dialog.setMessage("Actualizando datos...")
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun doInBackground(vararg params: Unit?) {

    }

    override fun onPostExecute(result: Unit) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    private fun createArticulosDialog() {
        val builder = AlertDialog.Builder(ctx)
        val inflater = LayoutInflater.from(ctx)
        builder.setView(inflater.inflate(R.layout.dialog_articulos_layout, null))
        builder.setPositiveButton("Agregar") { dialog, which -> }
        builder.setNegativeButton("Cancelar") { dialog, which -> }
        val dialog = builder.create()
        dialog.show()
    }

}