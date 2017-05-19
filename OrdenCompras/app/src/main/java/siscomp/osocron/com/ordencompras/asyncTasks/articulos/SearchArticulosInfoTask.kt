package siscomp.osocron.com.ordencompras.asyncTasks.articulos

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import siscomp.osocron.com.ordencompras.R
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.entities.Articulo
import siscomp.osocron.com.ordencompras.model.entities.Detallad
import siscomp.osocron.com.ordencompras.model.repositories.ArticulosRepo
import siscomp.osocron.com.ordencompras.model.repositories.DetalladRepo
import siscomp.osocron.com.ordencompras.model.repositories.ExistencRepo
import siscomp.osocron.com.ordencompras.model.repositories.PreciosRepo

/*
* This is an important class. It manages how articulos are searched
*
* 1. Search if the code is:
*       -Clave
*       -Clave + Detallado
*       -Codigo de Barras
* 2. Because codigos de barras are sometimes small we cannot be sure that
*    it is a clave or clave + detallad.
* 3. The order of the search must be the following:
*       -Search in Codigo de barras of Articulo table.
*       -Search in Codigo de barras de Detallad table.
*       -If it isn't a Codigo de barras check length of clave.
*       -If length is 8 search in Articulo table then check in Detallad and display to the user
*        the list of detallados.
*       -If length is greater than 8 search in Detallad directly taking the first 8 characters as clave
*        and the rest as detallad.
* 4. After the search is complete start the search for precios and existencias and ship the
*    result to the next task
* */
class SearchArticulosInfoTask(val ctx: Context,
                              val database: OrdenComprasDbHelper,
                              val clave: String) : AsyncTask<Unit, Unit, Pair<Articulo?, Detallad?>>() {

    val articuloRepo = ArticulosRepo(database)
    val detalladRepo = DetalladRepo(database)
    val preciosRepo = PreciosRepo(database)
    val existencRepo = ExistencRepo(database)

    val dialog = ProgressDialog(ctx)

    override fun onPreExecute() {
        dialog.setMessage("Buscando artículo...")
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun doInBackground(vararg params: Unit?): Pair<Articulo?, Detallad?>? {
        val a = articuloRepo.getByCodigoBarras(clave)
        if (a == null) {
            val b = detalladRepo.getByCodigoBarras(clave)
            if (b == null) {
                val length = clave.length
                if (length == 8) {
                    return Pair(articuloRepo.getById(clave), null)
                } else if (length > 8) {
                    val codigo = clave.take(8)
                    val subclave = clave.drop(8)
                    return Pair(articuloRepo.getById(codigo), detalladRepo.getBySubclave(codigo, subclave))
                } else {
                    return Pair(null, null)
                }
            } else {
                return Pair(articuloRepo.getById(b.clave), b)
            }
        } else {
            return Pair(a, null)
        }
    }

    override fun onPostExecute(result: Pair<Articulo?, Detallad?>) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        if (result.first != null && result.second != null) {
            ctx.toast("Detallado encontrado")
        } else if (result.first != null && result.second == null) {
            displayDetallados(result.first as Articulo)
        } else {
            ctx.toast("Articulo no encontrado")
        }
    }

    private fun displayDetallados(a: Articulo) {
        ctx.doAsync {
            val d = detalladRepo.getAllByClave(a.clave)
            if (d.isNotEmpty()) {
                uiThread {
                    //TODO create list of detallados
                }
            } else {
                //TODO Articulo found
            }
        }
    }

    private fun createArticulosDialog() {
        val builder = AlertDialog.Builder(ctx)
        val inflater = LayoutInflater.from(ctx)
        builder.setView(inflater.inflate(R.layout.dialog_articulos_layout, null))
        builder.setPositiveButton("Agregar") { dialog, which -> }
        builder.setNegativeButton("Cancelar") { dialog, which -> }
        val dialog = builder.create()
        val btnMinus = dialog.findViewById(R.id.buttonMenosCantidad) as Button
        val btnPlus = dialog.findViewById(R.id.buttonMasCantidad) as Button
        val edtCantidad = dialog.findViewById(R.id.editTextCantidad) as EditText
        dialog.show()
    }

}