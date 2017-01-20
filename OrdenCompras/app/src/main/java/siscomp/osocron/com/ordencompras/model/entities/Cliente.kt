package siscomp.osocron.com.ordencompras.model.entities

import org.jetbrains.anko.db.classParser

class Cliente(val clave: String,
              val nombre: String,
              val direccion: String,
              val nivel: String,
              val descuento: Double,
              val telefono1: String,
              val telefono2: String) : Comparable<Cliente> {

    override fun compareTo(other: Cliente): Int {
        val firstChar = if (!nombre.isEmpty()) nombre.first() else ' '
        val secondChar = if (!other.nombre.isEmpty()) other.nombre.first() else ' '
        if (firstChar == secondChar) return 0
        else if (firstChar < secondChar) return -1
        else return 1
    }

    override fun toString(): String {
        return nombre
    }
}

val clienteParser = classParser<Cliente>()
