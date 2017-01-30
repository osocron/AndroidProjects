package siscomp.osocron.com.ordencompras.asyncTasks.clientes

import android.app.ProgressDialog
import android.os.AsyncTask
import android.widget.Toast
import siscomp.osocron.com.ordencompras.ClientesActivity
import siscomp.osocron.com.ordencompras.model.entities.Cliente
import siscomp.osocron.com.ordencompras.model.json.JsonCliente
import siscomp.osocron.com.ordencompras.model.remote.ClienteRemoteRepo
import siscomp.osocron.com.ordencompras.model.repositories.ClientesRepo


class OneTimerClienteTask(val activity: ClientesActivity,
                          val remoteRepo: ClienteRemoteRepo,
                          val clienteRepo: ClientesRepo) : AsyncTask<Unit, Unit, Unit?>() {

    val dialog = ProgressDialog(activity)

    override fun onPreExecute() {
        dialog.setMessage("Proceso iniciado, no cerrar la aplicación!")
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun doInBackground(vararg p0: Unit?) {
        remoteRepo.getAll()?.map { toEntity(it) }?.forEach { clienteRepo.insert(it) }
    }

    override fun onPostExecute(result: Unit?) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        Toast.makeText(activity, "Operación finalizada", Toast.LENGTH_LONG).show()
    }

    fun toEntity(c: JsonCliente): Cliente {
        return Cliente(c.clave,
                c.nombre,
                c.direccion,
                c.nivel,
                c.descuento,
                c.telefono1,
                c.telefono2)
    }

}