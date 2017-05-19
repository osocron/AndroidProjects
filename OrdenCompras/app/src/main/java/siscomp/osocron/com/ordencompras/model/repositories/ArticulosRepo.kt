package siscomp.osocron.com.ordencompras.model.repositories

import org.jetbrains.anko.db.*
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.entities.Articulo
import siscomp.osocron.com.ordencompras.model.entities.articuloParser

class ArticulosRepo(val db: OrdenComprasDbHelper) : Repository<Articulo, String> {

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
                    "clave"      to t.clave,
                    "claverapid" to t.claverapid,
                    "barras1"    to t.barras1,
                    "barras2"    to t.barras2,
                    "barras3"    to t.barras3,
                    "gravado"    to t.gravado,
                    "descrgruma" to t.descrgruma,
                    "descripcio" to t.descripcio,
                    "umedida"    to t.umedida,
                    "piezas"     to t.piezas)
            if (res != -1L) t else null
        }
    }

    fun getByCodigoBarras(codigo: String): Articulo? {
        return db.use {
            select("Articulos").where("(barras1 = {barras}) or (barras2 = {barras}) or (barras3 = {barras})",
                    "barras" to codigo).exec {
                parseOpt(articuloParser)
            }
        }
    }

    override fun getById(id: String): Articulo? {
        return db.use {
            select("Articulos").where("(clave = {id})",
                    "id" to id).exec {
                parseOpt(articuloParser)
            }
        }
    }

    override fun update(t: Articulo): Articulo? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: String): Articulo? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}