package siscomp.osocron.com.ordencompras.model.repositories

import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import siscomp.osocron.com.ordencompras.model.db.OrdenComprasDbHelper
import siscomp.osocron.com.ordencompras.model.entities.Cliente
import siscomp.osocron.com.ordencompras.model.entities.clienteParser

class ClientesRepo(val db: OrdenComprasDbHelper) : Repository<Cliente, String> {

    override fun getAll(): List<Cliente> {
        return db.use {
            select("Clientes").exec {
                parseList(clienteParser)
            }
        }
    }

    override fun insert(t: Cliente): Cliente? {
        return db.use {
            val res = insert("Clientes",
                    "clave"     to t.clave,
                    "nombre"    to t.nombre,
                    "direccion" to t.direccion,
                    "nivel"     to t.nivel,
                    "descuento" to t.descuento,
                    "telefono1" to t.telefono1,
                    "telefono2" to t.telefono2)
            if (res != -1L) t else null
        }
    }

    fun searchQuery(s: String): List<Cliente>? {
        return db.use {
            val cursor = rawQuery("SELECT * FROM Clientes WHERE nombre LIKE '%$s%' LIMIT 50", arrayOf())
            val list: MutableList<Cliente> = mutableListOf()
            while (cursor.moveToNext()) {
                val clave = cursor.getString(0)
                val nombre = cursor.getString(1)
                val direccion = cursor.getString(2)
                val nivel = cursor.getString(3)
                val descuento = cursor.getDouble(4)
                val tele1 = cursor.getString(5)
                val tele2 = cursor.getString(6)
                val cliente = Cliente(clave, nombre, direccion, nivel, descuento.toFloat(), tele1, tele2)
                list.add(cliente)
            }
            cursor.close()
            list
        }
    }

    override fun getById(id: String): Cliente? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryAllByPredicate(p: (Cliente) -> Boolean): List<Cliente> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun querySingleByPredicate(p: (Cliente) -> Boolean): Cliente? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryIfExists(p: (Cliente) -> Boolean): Boolean {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(t: Cliente): Cliente? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: String): Cliente? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}