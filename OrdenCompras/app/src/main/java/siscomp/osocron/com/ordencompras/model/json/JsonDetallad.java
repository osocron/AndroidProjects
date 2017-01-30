package siscomp.osocron.com.ordencompras.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonDetallad {

    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("subclave")
    @Expose
    private String subclave;
    @SerializedName("claverapid")
    @Expose
    private String claverapid;
    @SerializedName("barras1")
    @Expose
    private String barras1;
    @SerializedName("barras2")
    @Expose
    private String barras2;
    @SerializedName("barras3")
    @Expose
    private String barras3;
    @SerializedName("descripcio")
    @Expose
    private String descripcio;

    /**
     * No args constructor for use in serialization
     *
     */
    public JsonDetallad() {
    }

    /**
     *
     * @param descripcio
     * @param barras3
     * @param barras2
     * @param claverapid
     * @param barras1
     * @param clave
     * @param subclave
     */
    public JsonDetallad(String clave, String subclave, String claverapid, String barras1, String barras2, String barras3, String descripcio) {
        super();
        this.clave = clave;
        this.subclave = subclave;
        this.claverapid = claverapid;
        this.barras1 = barras1;
        this.barras2 = barras2;
        this.barras3 = barras3;
        this.descripcio = descripcio;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getSubclave() {
        return subclave;
    }

    public void setSubclave(String subclave) {
        this.subclave = subclave;
    }

    public String getClaverapid() {
        return claverapid;
    }

    public void setClaverapid(String claverapid) {
        this.claverapid = claverapid;
    }

    public String getBarras1() {
        return barras1;
    }

    public void setBarras1(String barras1) {
        this.barras1 = barras1;
    }

    public String getBarras2() {
        return barras2;
    }

    public void setBarras2(String barras2) {
        this.barras2 = barras2;
    }

    public String getBarras3() {
        return barras3;
    }

    public void setBarras3(String barras3) {
        this.barras3 = barras3;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

}
