package fei.mx.uv.a2parcial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import fei.mx.uv.a2parcial.pojos.Usuario;
import fei.mx.uv.a2parcial.servicios.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edt_LoginCorreo) EditText edtCorreo;
    @BindView(R.id.edt_LoginPass) EditText edtPassword;
    @BindView(R.id.btn_LoginInitSesion) Button btnIniciasSesion;

    static final int REGISTRO_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REGISTRO_REQUEST) {
            String correo = data.getStringExtra("correo");
            String password = data.getStringExtra("password");
            edtCorreo.setText(correo);
            edtPassword.setText(password);
        }
    }

    public void iniciarSesion(View v) {
        String correo = edtCorreo.getText().toString();
        String password = edtPassword.getText().toString();
        if (!correo.isEmpty() && !password.isEmpty()) {
            logIn(correo, password);
        } else {
            if (correo.isEmpty())
                edtCorreo.setError("Campo obligatorio");
            else
                edtPassword.setError("Campo obligatorio");
        }
    }

    public void registrate(View v) {
        Intent intent = new Intent(MainActivity.this, ResgitroUsuario.class);
        startActivityForResult(intent, REGISTRO_REQUEST);
    }

    private void logIn(String correo, String password) {
        Call<Usuario> call = API.Factory.getIstance(MainActivity.this).login(correo, password);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.body() != null)
                    ingresar(response.body());
                else
                    Toast.makeText(MainActivity.this,
                            "Credenciales no validas, intente de nuevo",
                            Toast.LENGTH_LONG)
                            .show();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void ingresar(Usuario usuario) {
        Intent intent = new Intent(MainActivity.this, Notas.class);
        intent.putExtra("idUsuario", usuario.idUsuario);
        startActivity(intent);
    }

}
