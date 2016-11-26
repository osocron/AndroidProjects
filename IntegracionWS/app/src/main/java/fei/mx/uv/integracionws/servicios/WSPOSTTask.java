package fei.mx.uv.integracionws.servicios;


import android.os.AsyncTask;

import fei.mx.uv.integracionws.HttpUtils;
import fei.mx.uv.integracionws.pojos.Catalogo;

public class WSPOSTTask extends AsyncTask<Object, String, String>{

    @Override
    protected String doInBackground(Object... params) {
        return HttpUtils.setPOST(params[0].toString(), (Catalogo)params[1]);
    }

}
