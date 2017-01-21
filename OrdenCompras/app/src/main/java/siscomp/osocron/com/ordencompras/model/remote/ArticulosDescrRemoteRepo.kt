package siscomp.osocron.com.ordencompras.model.remote

import android.content.Context
import siscomp.osocron.com.ordencompras.model.json.ArticuloDescr


class ArticulosDescrRemoteRepo(val ctx: Context) : RemoteRepository<ArticuloDescr, String> {
    override fun getAll(): List<ArticuloDescr>? {
        val call = RestAPI.Factory.getIstance(ctx).articulosDescr()
        try {
            return call.execute().body()
        } catch (e: Exception) {
            return null
        }
    }

    override fun getById(id: String): ArticuloDescr? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}