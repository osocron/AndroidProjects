package siscomp.osocron.com.ordencompras.model.remote

import android.content.Context
import retrofit2.Call
import siscomp.osocron.com.ordencompras.model.json.JsonDetallad


class DetalladRemoteRepo(val ctx: Context) : RemoteRepository<JsonDetallad, String> {

    override val getAllCall: Call<List<JsonDetallad>>
        get() = RestAPI.Factory.getIstance(ctx).detallados()

    override fun getById(id: String): JsonDetallad? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getAllByClave(clave: String): List<JsonDetallad>? {
        return callAction(RestAPI.Factory.getIstance(ctx).detalladosPorClave(clave))
    }

}