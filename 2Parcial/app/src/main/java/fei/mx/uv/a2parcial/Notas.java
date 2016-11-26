package fei.mx.uv.a2parcial;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fei.mx.uv.a2parcial.pojos.Mensaje;
import fei.mx.uv.a2parcial.pojos.Nota;
import fei.mx.uv.a2parcial.servicios.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notas extends AppCompatActivity {

    @BindView(R.id.lv_Notas) ListView lvNotas;

    private ArrayAdapter<Nota> listAdapter;
    private int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        idUsuario = intent.getIntExtra("idUsuario", 0);
        obtenerNotas();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        obtenerNotas();
    }

    private void obtenerNotas() {
        Call<List<Nota>> call = API.Factory.getIstance(Notas.this).notasPorUsuario(idUsuario);
        call.enqueue(new Callback<List<Nota>>() {
            @Override
            public void onResponse(Call<List<Nota>> call, Response<List<Nota>> response) {
                if (response.body() != null) {
                    llenarLista(response.body());
                }
                else
                    Toast.makeText(Notas.this,
                            "Ninguna nota encontrada, crea unas cuantas!",
                            Toast.LENGTH_LONG)
                            .show();
            }

            @Override
            public void onFailure(Call<List<Nota>> call, Throwable t) {
                Toast.makeText(Notas.this,
                        t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void llenarLista(List<Nota> notas) {
        listAdapter = new ArrayAdapter<>(Notas.this, android.R.layout.simple_list_item_1, notas);
        lvNotas.setAdapter(listAdapter);
        lvNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editarNota((Nota) lvNotas.getItemAtPosition(i));
            }
        });
        lvNotas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                preguntarEliminar((Nota) lvNotas.getItemAtPosition(i), i);
                return true;
            }
        });
    }

    private void preguntarEliminar(final Nota nota, final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Notas.this);
        builder.setTitle("Elmimnar Nota");
        builder.setMessage("¿Seguro que quieres eliminar la nota?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                borrarNota(nota, pos);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Cancelado
            }
        });
        builder.create().show();
    }

    public void borrarNota(Nota nota, final int pos) {
        Call<Mensaje> call = API.Factory.getIstance(Notas.this).eliminarNota(nota.idNota);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                if (!response.body().error) {
                    listAdapter.remove(listAdapter.getItem(pos));
                    listAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(Notas.this,
                            response.body().mensaje,
                            Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                Toast.makeText(Notas.this,
                        t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void editarNota(Nota nota) {
        Intent intent = new Intent(Notas.this, RegistroNota.class);
        intent.putExtra("modo", "editar");
        intent.putExtra("idNota", nota.idNota);
        intent.putExtra("nombre", nota.nombre);
        intent.putExtra("descripcion", nota.descripcion);
        startActivity(intent);
    }

    public void nuevaNota(View v) {
        Intent intent = new Intent(Notas.this, RegistroNota.class);
        intent.putExtra("modo", "nueva");
        intent.putExtra("idUsuario", idUsuario);
        startActivity(intent);
    }

}
