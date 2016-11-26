package fei.mx.uv.a2parcial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import fei.mx.uv.a2parcial.pojos.Mensaje;
import fei.mx.uv.a2parcial.servicios.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroNota extends AppCompatActivity {

    @BindView(R.id.edtNombreNota) EditText edtNombre;
    @BindView(R.id.edtDescripcion) EditText edtDescripcion;

    private String modo;
    private int idNota;
    private int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_nota);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        modo = intent.getStringExtra("modo");
        establecerModo(intent);
    }

    private void establecerModo(Intent intent) {
        if (modo.equals("editar")) {
            idNota = intent.getIntExtra("idNota", 0);
            edtNombre.setText(intent.getStringExtra("nombre"));
            edtNombre.setEnabled(false);
            edtDescripcion.setText(intent.getStringExtra("descripcion"));
        } else {
            idUsuario = intent.getIntExtra("idUsuario", 0);
        }
    }

    public void guardarNota(View v) {
        if (modo.equals("nueva")) {
            nuevaNota();
        } else {
            modificarNota();
        }
    }

    private void modificarNota() {
        if (validarCampos()) {
            Call<Mensaje> call = API.Factory.getIstance(RegistroNota.this)
                    .modificarNota(idNota, edtDescripcion.getText().toString());
            call.enqueue(new Callback<Mensaje>() {
                @Override
                public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                    Toast.makeText(RegistroNota.this,
                            response.body().mensaje,
                            Toast.LENGTH_LONG)
                            .show();
                    finish();
                }

                @Override
                public void onFailure(Call<Mensaje> call, Throwable t) {
                    Toast.makeText(RegistroNota.this,
                            t.getMessage(),
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        }
    }

    private void nuevaNota() {
        if (validarCampos()) {
            Call<Mensaje> call =
                    API.Factory.getIstance(RegistroNota.this)
                            .nuevaNota(idUsuario,
                                    edtNombre.getText().toString(),
                                    edtDescripcion.getText().toString());
            call.enqueue(new Callback<Mensaje>() {
                @Override
                public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                    Toast.makeText(RegistroNota.this,
                            response.body().mensaje,
                            Toast.LENGTH_LONG)
                            .show();
                    finish();
                }

                @Override
                public void onFailure(Call<Mensaje> call, Throwable t) {
                    Toast.makeText(RegistroNota.this,
                            t.getMessage(),
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        }
    }

    private boolean validarCampos() {
        if (edtNombre.getText() == null) {
            edtNombre.setError("Campo obligatorio");
            return false;
        }
        else if (edtDescripcion.getText() == null) {
            edtDescripcion.setError("Campo obligatorio");
            return false;
        } else {
            return true;
        }
    }

    public void cancelarNota(View v) {
        finish();
    }

}
