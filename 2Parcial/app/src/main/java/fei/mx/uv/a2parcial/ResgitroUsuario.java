package fei.mx.uv.a2parcial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import fei.mx.uv.a2parcial.pojos.Mensaje;
import fei.mx.uv.a2parcial.servicios.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResgitroUsuario extends AppCompatActivity {

    @BindView(R.id.edt_RegistroNombre) EditText edtNombre;
    @BindView(R.id.edt_RegistroCorreo) EditText edtCorreo;
    @BindView(R.id.edt_RegistroPass) EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgitro_usuario);
        ButterKnife.bind(this);
    }

    public void registrarUsuario(View v) {
        String nombre = edtNombre.getText().toString();
        String correo = edtCorreo.getText().toString();
        String password = edtPassword.getText().toString();
        if (nombre.isEmpty())
            edtNombre.setError("Campo obligatorio");
        else if (correo.isEmpty())
            edtCorreo.setError("Campo obligatorio");
        else if (password.isEmpty())
            edtPassword.setError("Campo obligatorio");
        else
            registro(nombre, correo, password);
    }

    private void registro(String nombre, final String correo, final String password) {
        Call<Mensaje> call =
                API.Factory.getIstance(ResgitroUsuario.this)
                        .registrarUsuario(nombre, correo, password);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                if (!response.body().error) {
                    Toast.makeText(ResgitroUsuario.this,
                            response.body().mensaje,
                            Toast.LENGTH_LONG)
                            .show();
                    irALogin(correo, password);
                } else {
                    Toast.makeText(ResgitroUsuario.this,
                            response.body().mensaje,
                            Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                Toast.makeText(ResgitroUsuario.this,
                        t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void irALogin(String correo, String password) {
        Intent intent = new Intent();
        intent.putExtra("correo", correo);
        intent.putExtra("password", password);
        setResult(1, intent);
        finish();
    }

}
