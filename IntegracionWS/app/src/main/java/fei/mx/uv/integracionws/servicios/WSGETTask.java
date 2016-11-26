package fei.mx.uv.integracionws.servicios;


import android.os.AsyncTask;

import fei.mx.uv.integracionws.HttpUtils;

public class WSGETTask extends AsyncTask<Object, String, String> {

    @Override
    protected String doInBackground(Object... params) {
        return HttpUtils.getJSON(params[0].toString());
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
