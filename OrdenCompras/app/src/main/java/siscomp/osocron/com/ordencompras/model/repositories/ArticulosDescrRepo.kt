package siscomp.osocron.com.ordencompras.model.repositories

import org.jetbrains.anko.db.*
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.json.ArticuloDescr


class ArticulosDescrRepo(val db: OrdenComprasDbHelper) : Repository<ArticuloDescr, String> {

    val parser = classParser<ArticuloDescr>()

    override fun getAll(): List<ArticuloDescr> {
        return db.use {
            select("ArticulosDescr").exec {
                parseList(parser)
            }
        }
    }

    fun searchQuery(s: String): List<ArticuloDescr>? {
        return db.use {
            val cursor = rawQuery("SELECT * FROM ArticulosDescr WHERE descripcion LIKE '%$s%' LIMIT 50", arrayOf())
            val list: MutableList<ArticuloDescr> = mutableListOf()
            while (cursor.moveToNext()) {
                val clave = cursor.getString(0)
                val descripcion = cursor.getString(1)
                val articuloDescr = ArticuloDescr(clave, descripcion)
                list.add(articuloDescr)
            }
            cursor.close()
            list
        }
    }

    override fun insert(t: ArticuloDescr): ArticuloDescr? {
        return db.use {
            val res = insert("ArticulosDescr",
                    "clave" to t.clave,
                    "descripcion" to t.descripcion)
            if (res != -1L) t else null
        }
    }

    override fun getById(id: String): ArticuloDescr? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryAllByPredicate(p: (ArticuloDescr) -> Boolean): List<ArticuloDescr> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun querySingleByPredicate(p: (ArticuloDescr) -> Boolean): ArticuloDescr? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryIfExists(p: (ArticuloDescr) -> Boolean): Boolean {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(t: ArticuloDescr): ArticuloDescr? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: String): ArticuloDescr? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}