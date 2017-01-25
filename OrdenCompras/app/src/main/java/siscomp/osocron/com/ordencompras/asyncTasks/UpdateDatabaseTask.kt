package siscomp.osocron.com.ordencompras.asyncTasks

import android.app.ProgressDialog
import android.os.AsyncTask
import android.widget.Toast
import com.pixplicity.easyprefs.library.Prefs
import siscomp.osocron.com.ordencompras.ClientesActivity
import siscomp.osocron.com.ordencompras.model.entities.Cliente
import siscomp.osocron.com.ordencompras.model.repositories.ClientesRepo
import java.sql.Date
import java.util.*


class UpdateDatabaseTask(val activity: ClientesActivity,
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
        Prefs.putString("last_cliente_update", Date(Calendar.getInstance().timeInMillis).toString())
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        Toast.makeText(activity, "Proceso finalizado", Toast.LENGTH_LONG).show()
    }

}