package siscomp.osocron.com.ordencompras.asyncTasks.clientes

import android.app.ProgressDialog
import android.os.AsyncTask
import android.widget.Toast
import com.pixplicity.easyprefs.library.Prefs
import siscomp.osocron.com.ordencompras.ClientesActivity
import siscomp.osocron.com.ordencompras.asyncTasks.clientes.UpdateDatabaseTask
import siscomp.osocron.com.ordencompras.model.entities.Cliente
import siscomp.osocron.com.ordencompras.model.json.JsonCliente
import siscomp.osocron.com.ordencompras.model.repositories.ClientesRepo
import siscomp.osocron.com.ordencompras.model.remote.ClienteRemoteRepo
import java.sql.Date
import java.util.*


class UpdateClientTask(val activity: ClientesActivity,
                       val remoteRepo: ClienteRemoteRepo,
                       val clienteRepo: ClientesRepo) : AsyncTask<Void, Void, List<JsonCliente>?>() {

    val dialog = ProgressDialog(activity)

    override fun onPreExecute() {
        dialog.setMessage("Obteniendo datos...")
        dialog.setCancelable(true)
        dialog.setOnCancelListener { cancel(false) }
        dialog.show()
    }

    override fun doInBackground(vararg p0: Void?): List<JsonCliente>? {
        val fecha = Prefs.getString("last_cliente_update",
                Date(Calendar.getInstance().timeInMillis).toString())
        return remoteRepo.getNuevos(fecha)
    }

    override fun onPostExecute(result: List<JsonCliente>?) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        if (result != null) {
            val d = result.map { toEntity(it) }
            val task = UpdateDatabaseTask(activity, clienteRepo, d)
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