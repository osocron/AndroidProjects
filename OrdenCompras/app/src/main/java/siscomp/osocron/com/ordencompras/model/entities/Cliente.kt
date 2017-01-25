package siscomp.osocron.com.ordencompras.model.entities

import org.jetbrains.anko.db.classParser

class Cliente(val clave: String,
              val nombre: String,
              val direccion: String,
              val nivel: String,
              val descuento: Float,
              val telefono1: String,
              val telefono2: String) {

    override fun toString(): String {
        return nombre
    }
}

val clienteParser = classParser<Cliente>()
