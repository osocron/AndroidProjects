package siscomp.osocron.com.ordencompras.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Articulo {

    @SerializedName("clave")
    @Expose
    public String clave;

    @SerializedName("claverapid")
    @Expose
    public String claverapid;

    @SerializedName("barras1")
    @Expose
    public String barras1;

    @SerializedName("barras2")
    @Expose
    public String barras2;

    @SerializedName("barras3")
    @Expose
    public String barras3;

    @SerializedName("gravado")
    @Expose
    public int gravado;

    @SerializedName("descrgruma")
    @Expose
    public String descrgruma;

    @SerializedName("descripcio")
    @Expose
    public String descripcio;

    @SerializedName("umedida")
    @Expose
    public String umedida;

    @SerializedName("piezas")
    @Expose
    public float piezas;

    @SerializedName("fechaactua")
    @Expose
    public String fechaactua;

    /**
     * No args constructor for use in serialization
     *
     */
    public Articulo() {
    }

    /**
     *
     * @param descrgruma
     * @param fechaactua
     * @param descripcio
     * @param barras3
     * @param umedida
     * @param barras2
     * @param claverapid
     * @param barras1
     * @param piezas
     * @param clave
     * @param gravado
     */
    public Articulo(String clave, String claverapid, String barras1, String barras2, String barras3, int gravado, String descrgruma, String descripcio, String umedida, float piezas, String fechaactua) {
        super();
        this.clave = clave;
        this.claverapid = claverapid;
        this.barras1 = barras1;
        this.barras2 = barras2;
        this.barras3 = barras3;
        this.gravado = gravado;
        this.descrgruma = descrgruma;
        this.descripcio = descripcio;
        this.umedida = umedida;
        this.piezas = piezas;
        this.fechaactua = fechaactua;
    }

    @Override
    public String toString() {
        return descrgruma + ' ' + descripcio;
    }

}
