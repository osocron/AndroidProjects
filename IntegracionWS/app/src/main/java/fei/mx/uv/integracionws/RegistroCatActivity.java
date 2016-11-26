package fei.mx.uv.integracionws;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import fei.mx.uv.integracionws.pojos.Catalogo;
import fei.mx.uv.integracionws.pojos.Mensaje;
import fei.mx.uv.integracionws.servicios.WSPOSTTask;

public class RegistroCatActivity extends AppCompatActivity {

    private String URL_WS;
    private EditText edt_id;
    private EditText edt_idtipo;
    private EditText edt_nombre;
    private EditText edt_orden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cat);


        URL_WS = "http://192.168.1.64:8080/WebServicesDAR/webresources/catalogos/registro/";

        edt_id = (EditText) findViewById(R.id.edit_id);
        edt_idtipo = (EditText) findViewById(R.id.edt_tipo);
        edt_nombre = (EditText) findViewById(R.id.edt_nombre);
        edt_orden = (EditText) findViewById(R.id.edt_ordn);
    }

    public void cancelar(View v) {
        finish();
    }

    public void guardar(View v) {
        String id = this.edt_id.getText().toString();
        String idtipo = this.edt_idtipo.getText().toString();
        String nombre = this.edt_nombre.getText().toString();
        String orden = this.edt_orden.getText().toString();

        Catalogo c = new Catalogo();
        c.setIdCatalogo(!id.isEmpty() ? Integer.parseInt(id) : null);
        c.setIdTipo(!idtipo.isEmpty() ? Integer.parseInt(idtipo) : null);
        c.setNombre(nombre);
        c.setOrden(!orden.isEmpty() ? Integer.parseInt(orden) : null);

        WSPOSTTask task = (WSPOSTTask) new WSPOSTTask().execute(URL_WS, c);
        try {
            String message = task.get();
            Gson gson = new Gson();
            Mensaje mensaje = gson.fromJson(message, Mensaje.class);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(mensaje.getMensaje()).setTitle((mensaje.isError())?"Error":"Resultado");
            AlertDialog dialog = builder.create();
            dialog.show();

        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
    }


}
