package fei.mx.uv.a2parcial.pojos;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Usuario {

    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("idUsuario")
    @Expose
    public int idUsuario;
    @SerializedName("nombre")
    @Expose
    public String nombre;
    @SerializedName("password")
    @Expose
    public String password;

}
