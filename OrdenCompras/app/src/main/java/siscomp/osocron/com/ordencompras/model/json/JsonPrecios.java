package siscomp.osocron.com.ordencompras.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonPrecios {

    @SerializedName("empresa")
    @Expose
    private String empresa;
    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("subclave")
    @Expose
    private String subclave;
    @SerializedName("precio1")
    @Expose
    private float precio1;
    @SerializedName("precio2")
    @Expose
    private float precio2;
    @SerializedName("precio3")
    @Expose
    private float precio3;
    @SerializedName("cantidad1")
    @Expose
    private int cantidad1;
    @SerializedName("cantidad2")
    @Expose
    private int cantidad2;
    @SerializedName("cantidad3")
    @Expose
    private int cantidad3;

    /**
     * No args constructor for use in serialization
     *
     */
    public JsonPrecios() {
    }

    /**
     *
     * @param precio3
     * @param precio2
     * @param cantidad3
     * @param cantidad2
     * @param precio1
     * @param empresa
     * @param cantidad1
     * @param clave
     * @param subclave
     */
    public JsonPrecios(String empresa, String clave, String subclave, float precio1, float precio2, float precio3, int cantidad1, int cantidad2, int cantidad3) {
        super();
        this.empresa = empresa;
        this.clave = clave;
        this.subclave = subclave;
        this.precio1 = precio1;
        this.precio2 = precio2;
        this.precio3 = precio3;
        this.cantidad1 = cantidad1;
        this.cantidad2 = cantidad2;
        this.cantidad3 = cantidad3;
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

    public float getPrecio1() {
        return precio1;
    }

    public void setPrecio1(float precio1) {
        this.precio1 = precio1;
    }

    public float getPrecio2() {
        return precio2;
    }

    public void setPrecio2(float precio2) {
        this.precio2 = precio2;
    }

    public float getPrecio3() {
        return precio3;
    }

    public void setPrecio3(float precio3) {
        this.precio3 = precio3;
    }

    public int getCantidad1() {
        return cantidad1;
    }

    public void setCantidad1(int cantidad1) {
        this.cantidad1 = cantidad1;
    }

    public int getCantidad2() {
        return cantidad2;
    }

    public void setCantidad2(int cantidad2) {
        this.cantidad2 = cantidad2;
    }

    public int getCantidad3() {
        return cantidad3;
    }

    public void setCantidad3(int cantidad3) {
        this.cantidad3 = cantidad3;
    }

}
