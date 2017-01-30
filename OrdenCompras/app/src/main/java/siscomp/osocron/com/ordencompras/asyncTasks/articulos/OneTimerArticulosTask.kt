package siscomp.osocron.com.ordencompras.asyncTasks.articulos

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import siscomp.osocron.com.ordencompras.model.entities.Articulo
import siscomp.osocron.com.ordencompras.model.json.JsonArticulo
import siscomp.osocron.com.ordencompras.model.remote.ArticulosRemoteRepo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosDescrRepo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosRepo


class OneTimerArticulosTask(val ctx: Context,
                            val remoteRepo: ArticulosRemoteRepo,
                            val repo: ArticulosRepo) : AsyncTask<Unit, Unit, Unit>() {

    val dialog = ProgressDialog(ctx)

    override fun onPreExecute() {
        dialog.setMessage("Obteniendo datos...")
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun doInBackground(vararg params: Unit?) {
        val data = remoteRepo.getAll()
        data?.map { toEntity(it) }?.forEach { repo.insert(it) }
    }

    override fun onPostExecute(result: Unit?) {
        if (dialog.isShowing) {
            dialog.dismiss()
            Toast.makeText(ctx, "Operacion finalizada", Toast.LENGTH_LONG).show()
        }
    }

    fun toEntity(a: JsonArticulo): Articulo {
        return Articulo(a.clave,
                a.claverapid,
                a.barras1,
                a.barras2,
                a.barras3,
                a.gravado,
                a.descrgruma,
                a.descripcio,
                a.umedida,
                a.piezas)
    }

}