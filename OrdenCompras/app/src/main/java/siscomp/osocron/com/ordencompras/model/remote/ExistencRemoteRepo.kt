package siscomp.osocron.com.ordencompras.model.remote

import android.content.Context
import retrofit2.Call
import siscomp.osocron.com.ordencompras.GlobalValues
import siscomp.osocron.com.ordencompras.model.json.JsonExistenc


class ExistencRemoteRepo(val ctx: Context) : RemoteRepository<JsonExistenc, String> {

    override val getAllCall: Call<List<JsonExistenc>>
        get() = RestAPI.Factory.getIstance(ctx).existencias(GlobalValues.EMPRESA)

    override fun getById(id: String): JsonExistenc? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getAllByClave(clave: String): List<JsonExistenc>? {
        return callAction(RestAPI.Factory.getIstance(ctx).existenciasPorClave(GlobalValues.EMPRESA, clave))
    }

}