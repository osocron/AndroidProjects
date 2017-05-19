package siscomp.osocron.com.ordencompras.model.repositories

import org.jetbrains.anko.db.insert
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.entities.Existenc


class ExistencRepo(val db: OrdenComprasDbHelper) : Repository<Existenc, String> {

    override fun insert(t: Existenc): Existenc? {
        return db.use {
            val res =  insert("Existenc",
                    "empresa"    to t.empresa,
                    "clave"      to t.clave,
                    "subclave"   to t.subclave,
                    "existenact" to t.existenact)
            if (res != -1L) t else null
        }
    }

    override fun getAll(): List<Existenc> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getById(id: String): Existenc? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(t: Existenc): Existenc? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: String): Existenc? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}