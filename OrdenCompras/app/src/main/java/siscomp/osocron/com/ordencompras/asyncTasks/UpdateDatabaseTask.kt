package siscomp.osocron.com.ordencompras.asyncTasks

import android.app.ProgressDialog
import android.os.AsyncTask
import siscomp.osocron.com.ordencompras.ClientesActivity
import siscomp.osocron.com.ordencompras.model.entities.Cliente
import siscomp.osocron.com.ordencompras.model.repositories.ClientesRepo


class UpdateDatabaseTask(activity: ClientesActivity,
                         val clientesRepo: ClientesRepo,
                         val clientes: List<Cliente>) : AsyncTask<Void, Void, Unit>() {
    val dialog = ProgressDialog(activity)

    override fun onPreExecute() {
        dialog.setMessage("Actualizando cambios...")
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun doInBackground(vararg p0: Void?): Unit {
        clientes.forEach { c -> clientesRepo.insert(c) }
        return Unit
    }

    override fun onPostExecute(result: Unit?) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

}