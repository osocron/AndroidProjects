package siscomp.osocron.com.ordencompras.model.json;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Mensaje {

    @SerializedName("error")
    @Expose
    public boolean error;
    @SerializedName("mensaje")
    @Expose
    public String mensaje;

}
