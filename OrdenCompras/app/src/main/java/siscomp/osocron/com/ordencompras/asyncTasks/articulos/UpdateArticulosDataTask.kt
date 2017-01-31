package siscomp.osocron.com.ordencompras.asyncTasks.articulos

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.pixplicity.easyprefs.library.Prefs
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.entities.Articulo
import siscomp.osocron.com.ordencompras.model.entities.Detallad
import siscomp.osocron.com.ordencompras.model.entities.Existenc
import siscomp.osocron.com.ordencompras.model.entities.Precios
import siscomp.osocron.com.ordencompras.model.json.*
import siscomp.osocron.com.ordencompras.model.remote.ArticulosRemoteRepo
import siscomp.osocron.com.ordencompras.model.remote.DetalladRemoteRepo
import siscomp.osocron.com.ordencompras.model.remote.ExistencRemoteRepo
import siscomp.osocron.com.ordencompras.model.remote.PreciosRemoteRepo
import siscomp.osocron.com.ordencompras.model.repositories.*
import java.sql.Date
import java.util.*


class UpdateArticulosDataTask(val ctx: Context,
                              val database: OrdenComprasDbHelper) : AsyncTask<Unit, Unit, Unit>() {

    val dialog = ProgressDialog(ctx)
    val remoteArticulosRepo = ArticulosRemoteRepo(ctx)
    val articulosRepo = ArticulosRepo(database)
    val articulosDescrRepo = ArticulosDescrRepo(database)
    val remoteDetalladRepo = DetalladRemoteRepo(ctx)
    val detalladRepo = DetalladRepo(database)
    val remoteExistencRepo = ExistencRemoteRepo(ctx)
    val existencRepo = ExistencRepo(database)
    val remotePreciosRepo = PreciosRemoteRepo(ctx)
    val preciosRepo = PreciosRepo(database)

    override fun onPreExecute() {
        dialog.setMessage("Actualizando datos...")
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun doInBackground(vararg params: Unit) {
        val fecha = Prefs.getString("last_articulos_update",
                Date(Calendar.getInstance().timeInMillis).toString())
        //Get Articulos
        val articulos = remoteArticulosRepo.getNuevos(fecha)?.map { toArticulo(it) }
        //Insert them
        articulos?.forEach { articulosRepo.insert(it) }
        //Map them to descriptions and insert them
        articulos?.map { JsonArticuloDescr(it.clave, it.descrgruma + ' ' + it.descripcio) }?.forEach {
            articulosDescrRepo.insert(it)
        }
        //Get detallados and insert them
        articulos?.forEach {
            remoteDetalladRepo.getAllByClave(it.clave)?.map { toDetallad(it) }?.forEach {
                detalladRepo.insert(it)
            }
        }
        //Get Existencias and insert them
        articulos?.forEach {
            remoteExistencRepo.getAllByClave(it.clave)?.map { toExistenc(it) }?.forEach {
                existencRepo.insert(it)
            }
        }
        //Get precios and insert them
        articulos?.forEach {
            remotePreciosRepo.getAllByClave(it.clave)?.map { toPrecios(it) }?.forEach {
                preciosRepo.insert(it)
            }
        }
    }

    override fun onPostExecute(result: Unit) {
        Prefs.putString("last_articulos_update", Date(Calendar.getInstance().timeInMillis).toString())
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        Toast.makeText(ctx, "Datos actualizados", Toast.LENGTH_LONG).show()
    }

    private fun toArticulo(a: JsonArticulo): Articulo {
        return Articulo(a.clave,
                a.claverapid,
                a.barras1,
                a.barras2,
                a.barras3,
                a.gravado,
                a.descrgruma,
                a.descripcio,
                a.umedida,
                a.piezas)
    }

    private fun toDetallad(d: JsonDetallad): Detallad {
        return Detallad(d.clave, d.subclave, d.claverapid, d.barras1, d.barras2, d.barras3, d.descripcio)
    }

    private fun toExistenc(e: JsonExistenc): Existenc {
        return Existenc(e.empresa, e.clave, e.subclave, e.existenact.toDouble())
    }

    private fun toPrecios(p: JsonPrecios): Precios {
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