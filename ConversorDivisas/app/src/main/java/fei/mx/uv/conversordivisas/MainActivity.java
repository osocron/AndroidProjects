package fei.mx.uv.conversordivisas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import fei.mx.uv.conversordivisas.data.model.CurrencyRate;
import fei.mx.uv.conversordivisas.data.remote.SampleAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Float usd, cad, eur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Get data from remote API
        Call<CurrencyRate> call = SampleAPI.Factory.getIstance(MainActivity.this).getCurrency();
        call.enqueue(new Callback<CurrencyRate>() {
            @Override
            public void onResponse(Call<CurrencyRate> call, Response<CurrencyRate> response) {
                usd = response.body().rates.uSD;
                cad = response.body().rates.cAD;
                eur = response.body().rates.eUR;
            }

            @Override
            public void onFailure(Call<CurrencyRate> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "No se pudo acceder al servidor!",
                        Toast.LENGTH_LONG)
                        .show();
                usd = Float.valueOf("0.0");
                cad = Float.valueOf("0.0");
                eur = Float.valueOf("0.0");
            }
        });
    }

    public void onClickButton(View view) {
        EditText editText = (EditText) findViewById(R.id.editTextInput);
        if (editText.getText() != null && !editText.getText().toString().isEmpty()) {
            Double valor = Double.parseDouble(editText.getText().toString());
            Spinner spinner = (Spinner) findViewById(R.id.spinner);
            int pos = spinner.getSelectedItemPosition();
            Float rate;
            switch (pos) {
                case 0:
                    rate = usd;
                    break;
                case 1:
                    rate = cad;
                    break;
                case 2:
                    rate = eur;
                    break;
                default:
                    rate = Float.valueOf("0.0");
                    break;
            }
            Double conv = valor * rate;
            TextView resultado = (TextView) findViewById(R.id.textViewResultado);
            String res = String.format(Locale.US, "%.2f", conv);
            resultado.setText(String.format(getResources().getString(R.string.resultado), res));
        }
        else {
            editText.setError("Debes introducir un valor");
        }
    }

}
