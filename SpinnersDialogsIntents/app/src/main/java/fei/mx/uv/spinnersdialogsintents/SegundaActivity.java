package fei.mx.uv.spinnersdialogsintents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        Intent intent = getIntent();
        String carrera = intent.getStringExtra("val_carrera");
        String materia = intent.getStringExtra("val_materia");
        TextView tViewCarrera = (TextView) findViewById(R.id.textViewParam1);
        TextView tViewMateria = (TextView) findViewById(R.id.textViewParam2);
        tViewCarrera.setText(carrera);
        tViewMateria.setText(materia);
    }

}
