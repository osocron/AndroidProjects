package siscomp.osocron.com.ordencompras.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonCliente {

    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("nivel")
    @Expose
    private String nivel;
    @SerializedName("descuento")
    @Expose
    private float descuento;
    @SerializedName("telefono1")
    @Expose
    private String telefono1;
    @SerializedName("telefono2")
    @Expose
    private String telefono2;
    @SerializedName("fecha")
    @Expose
    private String fecha;

    /**
     * No args constructor for use in serialization
     *
     */
    public JsonCliente() {
    }

    /**
     *
     * @param nombre
     * @param direccion
     * @param descuento
     * @param fecha
     * @param telefono2
     * @param telefono1
     * @param clave
     * @param nivel
     */
    public JsonCliente(String clave, String nombre, String direccion, String nivel, float descuento, String telefono1, String telefono2, String fecha) {
        super();
        this.clave = clave;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nivel = nivel;
        this.descuento = descuento;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.fecha = fecha;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
