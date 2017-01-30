package siscomp.osocron.com.ordencompras.model.entities

import org.jetbrains.anko.db.classParser

class Detallad(val clave: String,
               val subclave: String,
               val claverapid: String,
               val barras1: String,
               val barras2: String,
               val barras3: String,
               val descripcio: String) {

    override fun toString(): String {
        return descripcio
    }
}

val detalladParser = classParser<Detallad>()

