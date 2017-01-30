package siscomp.osocron.com.ordencompras.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonEmpresas {

    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("sucursal")
    @Expose
    private String sucursal;

    @Override
    public String toString() {
        return sucursal;
    }
}
