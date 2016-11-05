package fei.mx.uv.spinnersdialogsintents;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private String carrera;
    private String materia;
    public String[] materias = {
            "DAR",
            "Base de Datos I",
            "Base de Datos II",
            "Ingenieria de Software",
            "Redes I",
            "Redes II",
            "Redes III"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materias);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void onClicked(View v) {
        Spinner spn_carrera = (Spinner) findViewById(R.id.spinner);
        Spinner spn_materia = (Spinner) findViewById(R.id.spinner2);
        carrera = spn_carrera.getSelectedItem().toString();
        materia = spn_materia.getSelectedItem().toString();
        //Mostrar dialogo
        AlertDialog dialogo = new AlertDialog.Builder(MainActivity.this).create();
        dialogo.setTitle("Valores seleccionados");
        dialogo.setMessage(String.format("Los valores seleccionados son:" +
                "\nCarrera: %s\nMateria: %s\n\n¿Deseea continuar?", carrera, materia));
        dialogo.setButton(AlertDialog.BUTTON_POSITIVE, "Acepatar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                llamarSegundaActivididad();
            }
        });
        dialogo.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogo.show();
    }

    private void llamarSegundaActivididad() {
        Intent intent = new Intent(this, SegundaActivity.class);
        intent.putExtra("val_carrera", carrera);
        intent.putExtra("val_materia", materia);
        startActivity(intent);
    }

}
