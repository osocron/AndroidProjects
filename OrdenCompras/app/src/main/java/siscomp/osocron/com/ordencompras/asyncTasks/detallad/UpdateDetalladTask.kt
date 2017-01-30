package siscomp.osocron.com.ordencompras.asyncTasks.detallad

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.pixplicity.easyprefs.library.Prefs
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.entities.Articulo
import siscomp.osocron.com.ordencompras.model.entities.Detallad
import siscomp.osocron.com.ordencompras.model.json.JsonDetallad
import siscomp.osocron.com.ordencompras.model.remote.DetalladRemoteRepo
import siscomp.osocron.com.ordencompras.model.repositories.DetalladRepo
import java.sql.Date
import java.util.*


class UpdateDetalladTask(val ctx: Context,
                         val data: List<Articulo>,
                         database: OrdenComprasDbHelper) : AsyncTask<Unit, Unit, Unit?>() {

    val dialog = ProgressDialog(ctx)
    val remoteRepo = DetalladRemoteRepo(ctx)
    val repo = DetalladRepo(database)

    override fun onPreExecute() {
        dialog.setMessage("Procesando datos...")
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun doInBackground(vararg p0: Unit?) {
        data.forEach {
            remoteRepo.getAllByClave(it.clave)?.map {
                toEntity(it)
            }?.forEach {
                repo.insert(it)
            }
        }
    }


    override fun onPostExecute(result: Unit?) {
        Prefs.putString("last_articulos_update", Date(Calendar.getInstance().timeInMillis).toString())
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        Toast.makeText(ctx, "Proceso finalizado", Toast.LENGTH_LONG).show()
    }

    private fun toEntity(d: JsonDetallad): Detallad {
        return Detallad(d.clave, d.subclave, d.claverapid, d.barras1, d.barras2, d.barras3, d.descripcio)
    }

}