package siscomp.osocron.com.ordencompras.model.repositories

import org.jetbrains.anko.db.insert
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.entities.Precios


class PreciosRepo(val db: OrdenComprasDbHelper) : Repository<Precios, String> {

    override fun insert(t: Precios): Precios? {
        return db.use {
            val res =  insert("Precios",
                    "empresa"    to t.empresa,
                    "clave"      to t.clave,
                    "subclave"   to t.subclave,
                    "precio1"    to t.precio1,
                    "precio2"    to t.precio2,
                    "precio3"    to t.precio3,
                    "cantidad1"  to t.cantidad1,
                    "cantidad2"  to t.cantidad2,
                    "cantidad3"  to t.cantidad3)
            if (res != -1L) t else null
        }
    }

    override fun getAll(): List<Precios> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getById(id: String): Precios? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryAllByPredicate(p: (Precios) -> Boolean): List<Precios> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun querySingleByPredicate(p: (Precios) -> Boolean): Precios? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryIfExists(p: (Precios) -> Boolean): Boolean {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(t: Precios): Precios? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: String): Precios? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}