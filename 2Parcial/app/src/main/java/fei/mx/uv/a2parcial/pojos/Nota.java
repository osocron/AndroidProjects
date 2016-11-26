package fei.mx.uv.a2parcial.pojos;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Nota {

    @SerializedName("descripcion")
    @Expose
    public String descripcion;
    @SerializedName("fechaCreacion")
    @Expose
    public String fechaCreacion;
    @SerializedName("idNota")
    @Expose
    public int idNota;
    @SerializedName("idUsuario")
    @Expose
    public int idUsuario;
    @SerializedName("nombre")
    @Expose
    public String nombre;

    @Override
    public String toString() {
        return this.nombre.toString();
    }
}
