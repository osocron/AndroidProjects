package siscomp.osocron.com.ordencompras.model.remote

import android.content.Context
import siscomp.osocron.com.ordencompras.model.entities.Cliente


class ClienteRemoteRepo(val ctx: Context) : RemoteRepository<Cliente, String> {

    override fun getAll(): List<Cliente>? {
        val call = RestAPI.Factory.getIstance(ctx).clientes()
        try {
            return call.execute().body()
        } catch (e: Exception) {
            return null
        }
    }

    override fun getById(id: String): Cliente? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}