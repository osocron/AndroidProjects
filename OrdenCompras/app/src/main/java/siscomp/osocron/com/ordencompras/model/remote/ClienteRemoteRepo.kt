package siscomp.osocron.com.ordencompras.model.remote

import android.content.Context
import retrofit2.Call
import siscomp.osocron.com.ordencompras.model.json.Cliente


class ClienteRemoteRepo(val ctx: Context) : RemoteRepository<Cliente, String> {

    override val getAllCall: Call<List<Cliente>>
        get() = RestAPI.Factory.getIstance(ctx).clientes()

    override fun getById(id: String): Cliente? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getNuevos(fecha: String): List<Cliente>? {
        return callAction(RestAPI.Factory.getIstance(ctx).nuevosClientes(fecha))
    }

}