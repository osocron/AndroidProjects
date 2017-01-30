package siscomp.osocron.com.ordencompras.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonExistenc {

    @SerializedName("empresa")
    @Expose
    private String empresa;
    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("subclave")
    @Expose
    private String subclave;
    @SerializedName("existenact")
    @Expose
    private float existenact;

    /**
     * No args constructor for use in serialization
     *
     */
    public JsonExistenc() {
    }

    /**
     *
     * @param existenact
     * @param empresa
     * @param clave
     * @param subclave
     */
    public JsonExistenc(String empresa, String clave, String subclave, float existenact) {
        super();
        this.empresa = empresa;
        this.clave = clave;
        this.subclave = subclave;
        this.existenact = existenact;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public float getExistenact() {
        return existenact;
    }

    public void setExistenact(float existenact) {
        this.existenact = existenact;
    }

}
