package siscomp.osocron.com.ordencompras.asyncTasks.precios

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import siscomp.osocron.com.ordencompras.model.entities.Precios
import siscomp.osocron.com.ordencompras.model.json.JsonPrecios
import siscomp.osocron.com.ordencompras.model.remote.PreciosRemoteRepo
import siscomp.osocron.com.ordencompras.model.repositories.PreciosRepo


class OneTimerPreciosTask(val ctx: Context,
                          val remoteRepo: PreciosRemoteRepo,
                          val repo: PreciosRepo) : AsyncTask<Unit, Unit, Unit?>() {

    val dialog = ProgressDialog(ctx)

    override fun onPreExecute() {
        dialog.setMessage("Proceso iniciado, no cerrar la aplicación!")
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun doInBackground(vararg p0: Unit?) {
        remoteRepo.getAll()?.map { toEntity(it) }?.forEach { repo.insert(it) }
    }

    override fun onPostExecute(result: Unit?) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        Toast.makeText(ctx, "Operación finalizada", Toast.LENGTH_LONG).show()
    }

    private fun toEntity(p: JsonPrecios): Precios {
        return Precios(p.empresa,
                p.clave,
                p.subclave,
                p.precio1.toDouble(),
                p.precio2.toDouble(),
                p.precio3.toDouble(),
                p.cantidad1,
                p.cantidad2,
                p.cantidad3)
    }

}