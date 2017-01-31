package siscomp.osocron.com.ordencompras.model.remote

import android.content.Context
import retrofit2.Call
import siscomp.osocron.com.ordencompras.GlobalValues
import siscomp.osocron.com.ordencompras.model.json.JsonPrecios


class PreciosRemoteRepo(val ctx: Context) : RemoteRepository<JsonPrecios, String> {

    override val getAllCall: Call<List<JsonPrecios>>
        get() = RestAPI.Factory.getIstance(ctx).precios()

    override fun getById(id: String): JsonPrecios? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getAllByClave(clave: String): List<JsonPrecios>? {
        return callAction(RestAPI.Factory.getIstance(ctx).preciosPorClave(clave))
    }

}