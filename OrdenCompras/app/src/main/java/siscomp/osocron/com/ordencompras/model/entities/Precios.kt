package siscomp.osocron.com.ordencompras.model.entities

import org.jetbrains.anko.db.classParser

class Precios(val empresa: String,
              val clave: String,
              val subclave: String,
              val precio1: Double,
              val precio2: Double,
              val precio3: Double,
              val cantidad1: Int,
              val cantidad2: Int,
              val cantidad3: Int)

val preciosParser = classParser<Precios>()
