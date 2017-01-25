package siscomp.osocron.com.ordencompras.asyncTasks

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.pixplicity.easyprefs.library.Prefs
import siscomp.osocron.com.ordencompras.model.json.Articulo
import siscomp.osocron.com.ordencompras.model.remote.ArticulosRemoteRepo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosDescrRepo
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosRepo
import java.sql.Date
import java.util.*


class UpdateArticulosTask(val ctx: Context,
                          val remoteRepo: ArticulosRemoteRepo,
                          val repo: ArticulosRepo,
                          val articulosDescrRepo: ArticulosDescrRepo) : AsyncTask<Unit, Unit, List<Articulo>?>(){

    val dialog = ProgressDialog(ctx)

    override fun onPreExecute() {
        dialog.setMessage("Obteniendo datos...")
        dialog.setCancelable(true)
        dialog.setOnCancelListener { cancel(false) }
        dialog.show()
    }

    override fun doInBackground(vararg p0: Unit?): List<Articulo>? {
        val fecha = Prefs.getString("last_articulos_update",
                Date(Calendar.getInstance().timeInMillis).toString())
        return remoteRepo.getNuevos(fecha)
    }

    override fun onPostExecute(result: List<Articulo>?) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        if (result != null) {
            val data = result.map { toEntity(it) }
            val task = UpdateArituclosDBTask(ctx, repo, data, articulosDescrRepo)
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

    fun toEntity(a: Articulo): siscomp.osocron.com.ordencompras.model.entities.Articulo {
        return siscomp.osocron.com.ordencompras.model.entities.Articulo(a.clave,
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