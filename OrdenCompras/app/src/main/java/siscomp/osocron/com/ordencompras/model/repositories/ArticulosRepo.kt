package siscomp.osocron.com.ordencompras.model.repositories

import org.jetbrains.anko.db.*
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.json.Articulo

class ArticulosRepo(val db: OrdenComprasDbHelper) : Repository<Articulo, String> {

    val articuloParser = rowParser { clave: String,
                                     claverapid: String,
                                     barras1: String,
                                     barras2: String,
                                     barras3: String,
                                     gravado: Int,
                                     descrgruma: String,
                                     descripcio: String,
                                     umedida: String,
                                     piezas: Float ->
        Articulo(clave, claverapid, barras1, barras2, barras3, gravado, descrgruma, descripcio, umedida, piezas)
    }

    override fun getAll(): List<Articulo> {
        return db.use {
            select("Articulos").exec {
                parseList(articuloParser)
            }
        }
    }

    override fun insert(t: Articulo): Articulo? {
        return db.use {
            val res =  insert("Articulos",
                    "clave" to t.clave,
                    "claverapid" to t.claverapid,
                    "barras1" to t.barras1,
                    "barras2" to t.barras2,
                    "barras3" to t.barras2,
                    "gravado" to t.gravado,
                    "descrgruma" to t.descrgruma,
                    "descripcio" to t.descripcio,
                    "umedida" to t.umedida,
                    "piezas" to t.piezas)
            if (res != -1L) t else null
        }
    }

    override fun getById(id: String): Articulo? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryAllByPredicate(p: (Articulo) -> Boolean): List<Articulo> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun querySingleByPredicate(p: (Articulo) -> Boolean): Articulo? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryIfExists(p: (Articulo) -> Boolean): Boolean {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(t: Articulo): Articulo? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: String): Articulo? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}