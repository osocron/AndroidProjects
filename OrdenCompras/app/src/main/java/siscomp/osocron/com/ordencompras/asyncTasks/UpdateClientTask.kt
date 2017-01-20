package siscomp.osocron.com.ordencompras.asyncTasks

import android.app.ProgressDialog
import android.os.AsyncTask
import android.widget.Toast
import siscomp.osocron.com.ordencompras.ClientesActivity
import siscomp.osocron.com.ordencompras.model.entities.Cliente
import siscomp.osocron.com.ordencompras.model.repositories.ClientesRepo
import siscomp.osocron.com.ordencompras.model.remote.ClienteRemoteRepo


class UpdateClientTask(val activity: ClientesActivity,
                       val remoteRepo: ClienteRemoteRepo,
                       val clienteRepo: ClientesRepo) : AsyncTask<Void, Void, List<Cliente>?>() {

    val dialog = ProgressDialog(activity)

    override fun onPreExecute() {
        dialog.setMessage("Obteniendo datos...")
        dialog.setCancelable(true)
        dialog.setOnCancelListener { cancel(false) }
        dialog.show()
    }

    override fun doInBackground(vararg p0: Void?): List<Cliente>? {
        return remoteRepo.getAll()
    }

    override fun onPostExecute(result: List<Cliente>?) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        if (result != null) {
            val task = UpdateDatabaseTask(activity, clienteRepo, result)
            task.execute()
        }
        else Toast.makeText(activity, "Error al obtener los datos", Toast.LENGTH_LONG).show()
    }

    override fun onCancelled() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        Toast.makeText(activity, "Operacion cancelada", Toast.LENGTH_LONG).show()
    }

}