package siscomp.osocron.com.ordencompras.model.entities

import org.jetbrains.anko.db.classParser

class Existenc(val empresa: String,
               val clave: String,
               val subclave: String,
               val existenact: Double)

val existencParser = classParser<Existenc>()
