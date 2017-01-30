package siscomp.osocron.com.ordencompras.asyncTasks.articulos

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.pixplicity.easyprefs.library.Prefs
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.entities.Articulo
import siscomp.osocron.com.ordencompras.model.json.JsonArticulo
import siscomp.osocron.com.ordencompras.model.remote.ArticulosRemoteRepo
import java.sql.Date
import java.util.*


class UpdateArticulosTask(val ctx: Context,
                          val database: OrdenComprasDbHelper) : AsyncTask<Unit, Unit, List<JsonArticulo>?>(){

    val dialog = ProgressDialog(ctx)
    val remoteRepo = ArticulosRemoteRepo(ctx)


    override fun onPreExecute() {
        dialog.setMessage("Obteniendo datos...")
        dialog.setCancelable(true)
        dialog.setOnCancelListener { cancel(false) }
        dialog.show()
    }

    override fun doInBackground(vararg p0: Unit?): List<JsonArticulo>? {
        val fecha = Prefs.getString("last_articulos_update",
                Date(Calendar.getInstance().timeInMillis).toString())
        return remoteRepo.getNuevos(fecha)
    }

    override fun onPostExecute(result: List<JsonArticulo>?) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        if (result != null) {
            val data = result.map { toEntity(it) }
            val task = UpdateArituclosDBTask(ctx, database, data)
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