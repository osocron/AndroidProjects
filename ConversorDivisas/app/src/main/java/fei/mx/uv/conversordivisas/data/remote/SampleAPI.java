package fei.mx.uv.conversordivisas.data.remote;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import fei.mx.uv.conversordivisas.data.model.CurrencyRate;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface SampleAPI {

    String BASE_URL = "http://api.fixer.io/";

    @GET("latest?base=MXN")
    Call<CurrencyRate> getCurrency();

    class Factory {
        private static SampleAPI service;

        public static SampleAPI getIstance(Context context) {
            if (service == null) {
                OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
                builder.readTimeout(15, TimeUnit.SECONDS);
                builder.connectTimeout(10, TimeUnit.SECONDS);
                builder.writeTimeout(10, TimeUnit.SECONDS);

                int cacheSize = 10 * 1024 * 1024; // 10 MiB
                Cache cache = new Cache(context.getCacheDir(), cacheSize);
                builder.cache(cache);

                Retrofit retrofit = new Retrofit.Builder()
                        .client(builder.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL).build();
                service = retrofit.create(SampleAPI.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
