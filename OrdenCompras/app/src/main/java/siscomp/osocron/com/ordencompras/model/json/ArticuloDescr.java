package siscomp.osocron.com.ordencompras.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticuloDescr {

    @SerializedName("clave")
    @Expose
    public String clave;
    @SerializedName("descripcion")
    @Expose
    public String descripcion;

    /**
     * No args constructor for use in serialization
     *
     */
    public ArticuloDescr() {
    }

    /**
     *
     * @param descripcion
     * @param clave
     */
    public ArticuloDescr(String clave, String descripcion) {
        super();
        this.clave = clave;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}