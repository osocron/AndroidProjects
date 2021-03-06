package siscomp.osocron.com.ordencompras.model.remote

import android.content.Context
import retrofit2.Call
import siscomp.osocron.com.ordencompras.model.json.JsonArticulo


class ArticulosRemoteRepo(val ctx: Context) : RemoteRepository<JsonArticulo, String> {

    override val getAllCall: Call<List<JsonArticulo>>
        get() = RestAPI.Factory.getIstance(ctx).articulos()

    override fun getById(id: String): JsonArticulo? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getNuevos(fecha: String): List<JsonArticulo>? {
        return callAction(RestAPI.Factory.getIstance(ctx).nuevosArticulos(fecha))
    }

}