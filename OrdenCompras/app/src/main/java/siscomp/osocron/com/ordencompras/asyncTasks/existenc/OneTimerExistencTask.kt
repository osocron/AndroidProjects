package siscomp.osocron.com.ordencompras.asyncTasks.existenc

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import siscomp.osocron.com.ordencompras.model.entities.Existenc
import siscomp.osocron.com.ordencompras.model.json.JsonExistenc
import siscomp.osocron.com.ordencompras.model.remote.ExistencRemoteRepo
import siscomp.osocron.com.ordencompras.model.repositories.ExistencRepo


class OneTimerExistencTask(val ctx: Context,
                           val remoteRepo: ExistencRemoteRepo,
                           val repo: ExistencRepo) : AsyncTask<Unit, Unit, Unit?>() {

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

    fun toEntity(c: JsonExistenc): Existenc {
        return Existenc(c.empresa,
                c.clave,
                c.subclave,
                c.existenact.toDouble())
    }

}