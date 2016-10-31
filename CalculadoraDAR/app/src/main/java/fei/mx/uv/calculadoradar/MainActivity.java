package fei.mx.uv.calculadoradar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView pantalla;
    private String valorPantalla;
    private Double valorTemp;
    /* 0 = No hay operacion
       1 = Dividir
       2 = Multiplicar
       3 = Sumar
       4 = Restar
     */
    private int operacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pantalla = (TextView) findViewById(R.id.textOutput);
        valorPantalla = "";
        valorTemp = 0.0;
    }

    public void onClickGenerico(View v) {

        switch (v.getId()) {
            case R.id.button0:
                valorPantalla += "0";
                break;
            case R.id.button1:
                valorPantalla += "1";
                break;
            case R.id.button2:
                valorPantalla += "2";
                break;
            case R.id.button3:
                valorPantalla += "3";
                break;
            case R.id.button4:
                valorPantalla += "4";
                break;
            case R.id.button5:
                valorPantalla += "5";
                break;
            case R.id.button6:
                valorPantalla += "6";
                break;
            case R.id.button7:
                valorPantalla += "7";
                break;
            case R.id.button8:
                valorPantalla += "8";
                break;
            case R.id.button9:
                valorPantalla += "9";
                break;
            case R.id.buttonPoint:
                if (!valorPantalla.contains("."))
                    valorPantalla += ".";
                break;
            case R.id.buttonLimpiar:
                valorPantalla = "";
                break;
            case R.id.buttonBorrar:
                if (valorPantalla.length() > 0)
                    valorPantalla = valorPantalla
                            .substring(0, valorPantalla.length() - 1);
                break;
            case R.id.buttonDiv:
                almacenarTemporal();
                operacion = 1;
                break;
            case R.id.buttonMult:
                break;
            case R.id.buttonPlus:
                break;
            case R.id.buttonSub:
                break;
            case R.id.buttonEq:
                hacerOperacion();
                break;
        }
        pantalla.setText(valorPantalla);
    }

    private void almacenarTemporal() {
        try {
            valorTemp = Double.parseDouble(valorPantalla);
            valorPantalla = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hacerOperacion() {
        if (operacion != 0) {
            try {
                Double resultado = 0.0;
                Double nuevoValor = Double.parseDouble(valorPantalla);
                switch (operacion) {
                    case 1:
                        if (nuevoValor != 0.0) {
                            resultado = valorTemp / nuevoValor;
                            valorPantalla = ""+resultado;
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "No se puede dividir entre cero",
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        break;
                    case 3: break;
                    case 4: break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
