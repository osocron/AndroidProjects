package siscomp.osocron.com.ordencompras.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class OrdenComprasDbHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "OrdenCompras.db", null, 1) {

    companion object {
        private var instance: OrdenComprasDbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): OrdenComprasDbHelper {
            if (instance == null) {
                instance = OrdenComprasDbHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable("Clientes", true,
                "clave" to TEXT + PRIMARY_KEY,
                "nombre" to TEXT,
                "direccion" to TEXT,
                "nivel" to TEXT,
                "descuento" to REAL,
                "telefono1" to TEXT,
                "telefono2" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable("Clientes", true)
    }

}

val Context.database: OrdenComprasDbHelper
    get() = OrdenComprasDbHelper.getInstance(applicationContext)