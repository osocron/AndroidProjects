package siscomp.osocron.com.ordencompras.model.entities

import org.jetbrains.anko.db.rowParser

class Articulo(val clave: String,
               val claverapid: String,
               val barras1: String,
               val barras2: String,
               val barras3: String,
               val gravado: Int,
               val descrgruma: String,
               val descripcio: String,
               val umedida: String,
               val piezas: Float) {

    override fun toString(): String {
        return descrgruma + ' ' + descripcio
    }
}

val articuloParser = rowParser { clave: String,
                                 claverapid: String,
                                 barras1: String,
                                 barras2: String,
                                 barras3: String,
                                 gravado: Int,
                                 descrgruma: String,
                                 descripcio: String,
                                 umedida: String,
                                 piezas: Float ->
    Articulo(clave, claverapid, barras1, barras2, barras3, gravado, descrgruma, descripcio, umedida, piezas)
}
