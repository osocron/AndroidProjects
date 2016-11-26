package fei.mx.uv.integracionws;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fei.mx.uv.integracionws.pojos.Catalogo;
import fei.mx.uv.integracionws.servicios.WSGETTask;

public class ListCatActivity extends AppCompatActivity {

    private String URL_WS;
    private EditText edtParam;
    private ListView listaCatalogos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cat);

        URL_WS = "http://192.168.1.64:8080/WebServicesDAR/webresources/catalogos/byIdtipo/";

        edtParam = (EditText) findViewById(R.id.editText);
        listaCatalogos = (ListView) findViewById(R.id.listaCatalogos);
    }

    public void consultarWS(View v) {
        String param = edtParam.getText().toString();
        WSGETTask task = (WSGETTask) new WSGETTask().execute(URL_WS + param);
        try {
            String message = task.get();
            Gson gson = new Gson();
            List<Catalogo> catalogos = gson.fromJson(message,
                    new TypeToken<List<Catalogo>>(){}.getType());
            actualizarListaCatalogos(catalogos);
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
    }

    private void actualizarListaCatalogos(List<Catalogo> catalogos) {
        List<String> elementos = new ArrayList<>();
        if (catalogos != null) {
            for (Catalogo c: catalogos) {
                elementos.add(c.getNombre());
            }
        }
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elementos);
        this.listaCatalogos.setAdapter(itemsAdapter);
    }


}
