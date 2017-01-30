package siscomp.osocron.com.ordencompras.asyncTasks.detallad

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import siscomp.osocron.com.ordencompras.model.entities.Detallad
import siscomp.osocron.com.ordencompras.model.json.JsonDetallad
import siscomp.osocron.com.ordencompras.model.remote.DetalladRemoteRepo
import siscomp.osocron.com.ordencompras.model.repositories.DetalladRepo


class OneTimerDetalladTask(val ctx: Context,
                           val remoteRepo: DetalladRemoteRepo,
                           val repo: DetalladRepo) : AsyncTask<Unit, Unit, Unit?>() {

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

    fun toEntity(c: JsonDetallad): Detallad {
        return Detallad(c.clave,
                c.subclave,
                c.claverapid,
                c.barras1,
                c.barras2,
                c.barras3,
                c.descripcio)
    }

}