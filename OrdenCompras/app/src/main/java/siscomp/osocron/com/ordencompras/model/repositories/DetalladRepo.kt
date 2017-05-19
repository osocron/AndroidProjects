package siscomp.osocron.com.ordencompras.model.repositories

import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.parseOpt
import org.jetbrains.anko.db.select
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.entities.Detallad
import siscomp.osocron.com.ordencompras.model.entities.detalladParser


class DetalladRepo(val db: OrdenComprasDbHelper) : Repository<Detallad, String> {

    override fun getAll(): List<Detallad> {
        return db.use {
            select("Detallad").exec {
                parseList(detalladParser)
            }
        }
    }

    override fun insert(t: Detallad): Detallad? {
        return db.use {
            val res =  insert("Detallad",
                    "clave"      to t.clave,
                    "subclave"   to t.subclave,
                    "claverapid" to t.claverapid,
                    "barras1"    to t.barras1,
                    "barras2"    to t.barras2,
                    "barras3"    to t.barras3,
                    "descripcio" to t.descripcio)
            if (res != -1L) t else null
        }
    }

    fun getByCodigoBarras(codigo: String): Detallad? {
        return db.use {
            select("Detallad").where("(barras1 = {barras}) or (barras2 = {barras}) or (barras3 = {barras})",
                    "barras" to codigo).exec {
                parseOpt(detalladParser)
            }
        }
    }

    fun getBySubclave(mClave: String, mSubclave: String): Detallad? {
        return db.use {
            select("Detallad").where("(clave = {mClave}) and (subclave = {mSubclave})",
                    "mClave" to mClave,
                    "mSubclave" to mSubclave).exec {
                parseOpt(detalladParser)
            }
        }
    }

    fun getAllByClave(mClave: String): List<Detallad> {
        return db.use {
            select("Detallad").where("(clave = {mClave})",
                    "mClave" to mClave).exec {
                parseList(detalladParser)
            }
        }
    }

    override fun getById(id: String): Detallad? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(t: Detallad): Detallad? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: String): Detallad? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}