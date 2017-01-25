package siscomp.osocron.com.ordencompras.model.remote

import android.content.Context
import retrofit2.Call
import siscomp.osocron.com.ordencompras.model.json.Articulo


class ArticulosRemoteRepo(val ctx: Context) : RemoteRepository<Articulo, String> {

    override val getAllCall: Call<List<Articulo>>
        get() = RestAPI.Factory.getIstance(ctx).articulos()

    override fun getById(id: String): Articulo? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getNuevos(fecha: String): List<Articulo>? {
        return callAction(RestAPI.Factory.getIstance(ctx).nuevosArticulos(fecha))
    }

}