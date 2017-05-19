package siscomp.osocron.com.ordencompras.model.repositories

import org.jetbrains.anko.db.*
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.json.JsonArticuloDescr


class ArticulosDescrRepo(val db: OrdenComprasDbHelper) : Repository<JsonArticuloDescr, String> {

    val parser = classParser<JsonArticuloDescr>()

    override fun getAll(): List<JsonArticuloDescr> {
        return db.use {
            select("ArticulosDescr").exec {
                parseList(parser)
            }
        }
    }

    fun searchQuery(s: String): List<JsonArticuloDescr>? {
        return db.use {
            val cursor = rawQuery("SELECT * FROM ArticulosDescr WHERE descripcion LIKE '%$s%' LIMIT 50", arrayOf())
            val list: MutableList<JsonArticuloDescr> = mutableListOf()
            while (cursor.moveToNext()) {
                val clave = cursor.getString(0)
                val descripcion = cursor.getString(1)
                val articuloDescr = JsonArticuloDescr(clave, descripcion)
                list.add(articuloDescr)
            }
            cursor.close()
            list
        }
    }

    override fun insert(t: JsonArticuloDescr): JsonArticuloDescr? {
        return db.use {
            val res = insert("ArticulosDescr",
                    "clave" to t.clave,
                    "descripcion" to t.descripcion)
            if (res != -1L) t else null
        }
    }

    override fun getById(id: String): JsonArticuloDescr? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(t: JsonArticuloDescr): JsonArticuloDescr? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: String): JsonArticuloDescr? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}