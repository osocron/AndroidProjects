package siscomp.osocron.com.ordencompras.model.remote

import android.content.Context
import siscomp.osocron.com.ordencompras.model.json.Articulo


class ArticulosRemoteRepo(val ctx: Context) : RemoteRepository<Articulo, String> {

    override fun getAll(): List<Articulo>? {
        val call = RestAPI.Factory.getIstance(ctx).articulos()
        try {
            return call.execute().body()
        } catch (e: Exception) {
            return null
        }
    }

    override fun getById(id: String): Articulo? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}