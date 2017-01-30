package siscomp.osocron.com.ordencompras.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonArticuloDescr {

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
    public JsonArticuloDescr() {
    }

    /**
     *
     * @param descripcion
     * @param clave
     */
    public JsonArticuloDescr(String clave, String descripcion) {
        super();
        this.clave = clave;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}