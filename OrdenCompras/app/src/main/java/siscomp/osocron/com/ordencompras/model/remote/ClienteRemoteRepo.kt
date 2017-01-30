package siscomp.osocron.com.ordencompras.model.remote

import android.content.Context
import retrofit2.Call
import siscomp.osocron.com.ordencompras.model.json.JsonCliente


class ClienteRemoteRepo(val ctx: Context) : RemoteRepository<JsonCliente, String> {

    override val getAllCall: Call<List<JsonCliente>>
        get() = RestAPI.Factory.getIstance(ctx).clientes()

    override fun getById(id: String): JsonCliente? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getNuevos(fecha: String): List<JsonCliente>? {
        return callAction(RestAPI.Factory.getIstance(ctx).nuevosClientes(fecha))
    }

}